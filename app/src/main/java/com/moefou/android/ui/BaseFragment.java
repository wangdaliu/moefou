package com.moefou.android.ui;

import android.support.v4.app.Fragment;

import com.moefou.android.util.NetworkDetector;

public class BaseFragment extends Fragment {

    protected boolean isOnline() {
        NetworkDetector networkDetector = new NetworkDetector(getActivity());
        return networkDetector.isDataConnectionAvailable();
    }
}
