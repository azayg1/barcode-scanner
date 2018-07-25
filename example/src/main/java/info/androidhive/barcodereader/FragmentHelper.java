package info.androidhive.barcodereader;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;



/**
 * Created by user on 6/4/2015.
 * FragmentHelper
 */
public class FragmentHelper {
    public static void replaceFragment(FragmentManager l_fragmentManager, Fragment p_fragment, int p_container_id, String tag) {

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.replace(p_container_id, p_fragment, tag);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void replaceFragment(FragmentManager l_fragmentManager, Fragment p_fragment, int p_container_id) {
        replaceFragment(l_fragmentManager, p_fragment, p_container_id, p_fragment.getClass().getName());
    }

    public static void removeFragment(FragmentManager l_fragmentManager, Fragment fragment, int p_container_id) {

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.replace(p_container_id, fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void addFragment(FragmentManager l_fragmentManager, Fragment p_fragment, int p_container_id) {

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.add(p_container_id, p_fragment, p_fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void addDialog(FragmentManager l_fragmentManager, Fragment p_fragment) {
        if (l_fragmentManager == null || p_fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.add(p_fragment, p_fragment.getClass().getName());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void addDialog(FragmentManager l_fragmentManager, Fragment p_fragment, String tagName) {
        if (l_fragmentManager == null || p_fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.add(p_fragment, tagName);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commitAllowingStateLoss();
    }

    public static void removeDialog(FragmentManager l_fragmentManager, Fragment p_fragment) {
        if (l_fragmentManager == null || p_fragment == null) {
            return;
        }

        FragmentTransaction fragmentTransaction = l_fragmentManager.beginTransaction();
        fragmentTransaction.remove(p_fragment);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
