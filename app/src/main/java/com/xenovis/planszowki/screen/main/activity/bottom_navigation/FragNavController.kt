package com.xenovis.planszowki.screen.main.activity.bottom_navigation

import android.os.Bundle
import android.support.annotation.IdRes
import android.support.annotation.IntDef
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import org.json.JSONArray
import java.util.*

/**
 * The class is used to manage navigation through multiple stacks of fragments, as well as coordinate
 * fragments that may appear on screen
 * Created by niccapdevila on 3/21/16.
 */

class FragNavController(
        savedInstanceState: Bundle?,
        private val mFragmentManager: FragmentManager,
        @IdRes val mContainerId: Int,
        baseFragments: List<Fragment>
) {

    constructor(savedInstanceState: Bundle, fragmentManager: FragmentManager, @IdRes containerId: Int, baseFragments: List<Fragment>, @Transit transitionMode: Int) : this(savedInstanceState, fragmentManager, containerId, baseFragments) {
        this.transitionMode = transitionMode
    }

    private val mFragmentStacks: MutableList<Stack<Fragment>>
    @TabIndex internal var mSelectedTabIndex = -1
    private var mTagCount: Int = 0
    private var mCurrentFrag: Fragment? = null
    private var transitionMode = FragmentTransaction.TRANSIT_ENTER_MASK

    init {
        instance = this

        mFragmentStacks = ArrayList<Stack<Fragment>>(baseFragments.size)

        if (savedInstanceState == null) {
            for (fragment in baseFragments) {
                val stack = Stack<Fragment>()
                stack.add(fragment)
                mFragmentStacks.add(stack)
            }
        } else {
            onRestoreFromBundle(savedInstanceState, baseFragments)
        }
    }

    private fun onRestoreFromBundle(savedInstanceState: Bundle, baseFragments: List<Fragment>) {

        // Restore selected tab
        val tabIndex = savedInstanceState.getInt(EXTRA_SELECTED_TAB_INDEX, -1)
        mSelectedTabIndex =  if (tabIndex != -1) tabIndex else TAB1

        // Restore tag count
        mTagCount = savedInstanceState.getInt(EXTRA_TAG_COUNT, 0)

        // Restore current fragment
        mCurrentFrag = mFragmentManager.findFragmentByTag(savedInstanceState.getString(EXTRA_CURRENT_FRAGMENT))

        // Restore fragment stacks
        try {
            val stackArrays = JSONArray(savedInstanceState.getString(EXTRA_FRAGMENT_STACK))

            for (x in 0..stackArrays.length() - 1) {
                val stackArray = stackArrays.getJSONArray(x)
                val stack = Stack<Fragment>()

                if (stackArray.length() == 1) {
                    val tag = stackArray.getString(0)
                    val fragment: Fragment?

                    if (tag == null || "null".equals(tag, ignoreCase = true)) {
                        fragment = baseFragments[x]
                    } else {
                        fragment = mFragmentManager.findFragmentByTag(tag)
                    }

                    if (fragment != null) {
                        stack.add(fragment)
                    }
                } else {
                    (0..stackArray.length() - 1)
                            .map { stackArray.getString(it) }
                            .filter { it != null && !"null".equals(it, ignoreCase = true) }
                            .mapNotNullTo(stack) { mFragmentManager.findFragmentByTag(it) }
                }

                mFragmentStacks.add(stack)
            }
        } catch (t: Throwable) {
            // Nothing we can do
        }

    }

    fun onSaveInstanceState(outState: Bundle) {

        // Write tag count
        outState.putInt(EXTRA_TAG_COUNT, mTagCount)
        //Log.e("FRAGNAV", "onSaveInstanceState: TAGCOUNT : " + mTagCount);

        // Write select tab
        outState.putInt(EXTRA_SELECTED_TAB_INDEX, mSelectedTabIndex)
        //Log.e("FRAGNAV", "onSaveInstanceState: TAB INDEX : " + mSelectedTabIndex);

        // Write current fragment
        if (mCurrentFrag != null) {
            //Log.e("FRAGNAV", "onSaveInstanceState: FRAGMENT : " + mCurrentFrag);
            outState.putString(EXTRA_CURRENT_FRAGMENT, mCurrentFrag!!.tag)
        }

        // Write stacks
        try {
            val stackArrays = JSONArray()

            for (stack in mFragmentStacks) {
                val stackArray = JSONArray()

                for (fragment in stack) {
                    stackArray.put(fragment.tag)
                }

                stackArrays.put(stackArray)
            }

            outState.putString(EXTRA_FRAGMENT_STACK, stackArrays.toString())
        } catch (t: Throwable) {
            // Nothing we can do
        }

    }

    @IntDef(FragmentTransaction.TRANSIT_NONE.toLong(), FragmentTransaction.TRANSIT_FRAGMENT_OPEN.toLong(), FragmentTransaction.TRANSIT_FRAGMENT_CLOSE.toLong(), FragmentTransaction.TRANSIT_FRAGMENT_FADE.toLong())
    @Retention(AnnotationRetention.SOURCE)
    private annotation class Transit

    /**
     * Switch to a different tab. Should not be called on the current tab.

     * @param index the index of the tab to switch to
     */
    fun switchTab(@TabIndex index: Int) {
        //Check to make sure the tab is within range
        if (index >= mFragmentStacks.size) {
            throw IndexOutOfBoundsException("Can't switch to a tab that hasn't been initialized. " + "Make sure to create all of the tabs you need in the Constructor")
        }
        if (mSelectedTabIndex != index) {
            mSelectedTabIndex = index

            val ft = mFragmentManager.beginTransaction()
            ft.setTransition(transitionMode)

            detachCurrentFragment(ft)

            //Attempt to reattach previous fragment
            //Fragment fragment = showPreviousFragment(ft);
            var fragment = reattachPreviousFragment(ft)
            if (fragment != null) {
                ft.commitAllowingStateLoss()
            } else {
                fragment = mFragmentStacks[mSelectedTabIndex].peek()
                ft.add(mContainerId, fragment, generateTag(fragment))
                ft.commitAllowingStateLoss()
            }
            mFragmentManager.executePendingTransactions()
            mCurrentFrag = fragment
        }
    }

    /**
     * Push a fragment onto the current stack

     * @param fragment The fragment that is to be pushed
     */
    fun push(fragment: Fragment?) {
        if (fragment != null) {

            val ft = mFragmentManager.beginTransaction()
            ft.setTransition(transitionMode)
            detachCurrentFragment(ft)
            ft.add(mContainerId, fragment, generateTag(fragment))
            ft.commit()

            mFragmentManager.executePendingTransactions()
            mFragmentStacks[mSelectedTabIndex].push(fragment)

            mCurrentFrag = fragment
        }
    }

    /**
     * Pop the current fragment from the current tab
     */
    fun pop() {
        val poppingFrag = currentFrag
        if (poppingFrag != null) {
            val ft = mFragmentManager.beginTransaction()
            ft.setTransition(transitionMode)
            ft.remove(poppingFrag)

            //overly cautious fragment pop
            val fragmentStack = mFragmentStacks[mSelectedTabIndex]
            if (!fragmentStack.isEmpty()) {
                fragmentStack.pop()
            }

            //Attempt reattach, if we can't, try to pop from the stack and push that on
            var fragment = reattachPreviousFragment(ft)
            if (fragment == null && !fragmentStack.isEmpty()) {
                fragment = fragmentStack.peek()
                ft.add(mContainerId, fragment, fragment!!.tag)

            }

            //Commit our transactions
            ft.commit()

            mFragmentManager.executePendingTransactions()
            mCurrentFrag = fragment
        }
    }

    /**
     * Clears the current tab's stack to get to just the bottom Fragment.
     */
    fun clearStack() {
        //Grab Current stack
        val fragmentStack = mFragmentStacks[mSelectedTabIndex]

        // Only need to start popping and reattach if the stack is greater than 1
        if (fragmentStack.size > 1) {
            var fragment: Fragment?
            val ft = mFragmentManager.beginTransaction()
            ft.setTransition(transitionMode)

            //Pop all of the fragments on the stack and remove them from the FragmentManager
            while (fragmentStack.size > 1) {
                fragment = mFragmentManager.findFragmentByTag(fragmentStack.peek().tag)
                if (fragment != null) {
                    fragmentStack.pop()
                    ft.remove(fragment)
                }
            }

            //Attempt to reattach previous fragment
            fragment = reattachPreviousFragment(ft)
            //If we can't reattach, either pull from the stack, or create a new base fragment
            if (fragment != null) {
                ft.commit()
            } else {
                if (!fragmentStack.isEmpty()) {
                    fragment = fragmentStack.peek()
                    ft.add(mContainerId, fragment, fragment!!.tag)
                    ft.commitAllowingStateLoss()
                }
            }

            //Update the stored version we have in the list
            mFragmentStacks[mSelectedTabIndex] = fragmentStack

            mCurrentFrag = fragment
        }
    }

    fun clearStackForTab(position: Int) {
        //Grab Current stack
        val fragmentStack = mFragmentStacks[position]

        // Only need to start popping and reattach if the stack is greater than 1
        if (fragmentStack.size > 1) {
            var fragment: Fragment?
            val ft = mFragmentManager.beginTransaction()
            ft.setTransition(transitionMode)

            //Pop all of the fragments on the stack and remove them from the FragmentManager
            while (fragmentStack.size > 1) {
                fragment = mFragmentManager.findFragmentByTag(fragmentStack.peek().tag)
                if (fragment != null) {
                    fragmentStack.pop()
                    ft.remove(fragment)
                }
            }

            //Attempt to reattach previous fragment
            fragment = reattachPreviousFragment(ft)
            //If we can't reattach, either pull from the stack, or create a new base fragment
            if (fragment != null) {
                ft.commit()
            } else {
                if (!fragmentStack.isEmpty()) {
                    fragment = fragmentStack.peek()
                    ft.add(mContainerId, fragment, fragment!!.tag)
                    ft.commitAllowingStateLoss()
                }
            }

            //Update the stored version we have in the list
            mFragmentStacks[position] = fragmentStack

            mCurrentFrag = fragment
        }
    }

    //Todo handle other types of fragments
    /*    public void showBottomSheet(BottomSheetDialogFragment bottomSheetDialogFragment) {
        bottomSheetDialogFragment.show(mFragmentManager, bottomSheetDialogFragment.getClass().getName() + ++mTagCount);
    }*/

    /**
     * Will attempt to reattach a previous fragment in the FragmentManager, or return null if not able to,

     * @param ft current fragment transaction
     * *
     * @return Fragment if we were able to find and reattach it
     */
    private fun reattachPreviousFragment(ft: FragmentTransaction): Fragment? {
        val fragmentStack = mFragmentStacks[mSelectedTabIndex]
        var fragment: Fragment? = null
        if (!fragmentStack.isEmpty()) {
            fragment = mFragmentManager.findFragmentByTag(fragmentStack.peek().tag)
            if (fragment != null) {
                ft.attach(fragment)
                //ft.show(fragment);
            }
        }
        return fragment
    }

    private fun showPreviousFragment(ft: FragmentTransaction): Fragment? {
        val fragmentStack = mFragmentStacks[mSelectedTabIndex]
        var fragment: Fragment? = null
        if (!fragmentStack.isEmpty()) {
            fragment = mFragmentManager.findFragmentByTag(fragmentStack.peek().tag)
            if (fragment != null) {
                ft.show(fragment)
            }
        }
        return fragment
    }

    /**
     * Attemps to detach any current fragment if it exists, and if none is found, returns;

     * @param ft the current transaction being performed
     */
    private fun detachCurrentFragment(ft: FragmentTransaction) {
        val oldFrag = currentFrag
        if (oldFrag != null) {
            ft.detach(oldFrag)
            //ft.hide(oldFrag);
        }
    }

    private fun hideCurrentFragment(ft: FragmentTransaction) {
        val oldFrag = currentFrag
        if (oldFrag != null) {
            ft.hide(oldFrag)
        }
    }

    private //Attempt to used stored current fragment
            //if not, try to pull it from the stack
    val currentFrag: Fragment?
        get() {
            if (mCurrentFrag != null) {
                return mCurrentFrag
            } else {
                val fragmentStack = mFragmentStacks[mSelectedTabIndex]
                if (!fragmentStack.isEmpty()) {
                    return mFragmentManager.findFragmentByTag(mFragmentStacks[mSelectedTabIndex].peek().tag)
                } else {
                    return null
                }
            }
        }

    /**
     * Create a unique fragment tag so that we can grab the fragment later from the FragmentManger

     * @param fragment The fragment that we're creating a unique tag for
     * *
     * @return a unique tag using the fragment's class name
     */
    private fun generateTag(fragment: Fragment): String {
        return fragment.javaClass.name + ++mTagCount
    }

    val currentStack: Stack<Fragment>
        get() = mFragmentStacks[mSelectedTabIndex]

    //Define the list of accepted constants
    @IntDef(TAB1.toLong(), TAB2.toLong(), TAB3.toLong(), TAB4.toLong(), TAB5.toLong())

    //Tell the compiler not to store annotation data in the .class file
    @Retention(AnnotationRetention.SOURCE)
    annotation //Declare the TabIndex annotation
    class TabIndex

    companion object {

        lateinit var instance : FragNavController
        //Declare the constants
        const val TAB1 = 0
        const val TAB2 = 1
        const val TAB3 = 2
        const val TAB4 = 3
        const val TAB5 = 4

        private val EXTRA_TAG_COUNT = FragNavController::class.java.name + ":EXTRA_TAG_COUNT"
        private val EXTRA_SELECTED_TAB_INDEX = FragNavController::class.java.name + ":EXTRA_SELECTED_TAB_INDEX"
        private val EXTRA_CURRENT_FRAGMENT = FragNavController::class.java.name + ":EXTRA_CURRENT_FRAGMENT"
        private val EXTRA_FRAGMENT_STACK = FragNavController::class.java.name + ":EXTRA_FRAGMENT_STACK"
    }
}