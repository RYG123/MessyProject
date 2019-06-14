package linkjob.com.rxbdtest;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import linkjob.com.rxbdtest.databinding.FragmentTestBinding;

public class FragmenTest2 extends Fragment {
    private FragmentTestBinding fragmentTestBinding;
    private RxTest rxTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentTestBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_test,container,false);
        View view = fragmentTestBinding.getRoot();
        databindingTest();

        return view;
    }

    private void databindingTest() {
        RxTest.getmInstance().getObservable()
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("tag", "onSubcribe");
                    }

                    @Override
                    public void onNext(String s) {
                        Log.i("tag", "onNext");
                        fragmentTestBinding.text.setText(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("tag", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.i("tag", "onComplete");
                    }
                });
    }

}