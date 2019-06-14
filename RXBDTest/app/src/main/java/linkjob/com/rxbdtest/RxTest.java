package linkjob.com.rxbdtest;


import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.subjects.Subject;

public class RxTest {
    private static RxTest mInstance;
    private Subject<String> mSubject;
    private RxTest(){
        mSubject = ReplaySubject.create();
    }
    public static RxTest getmInstance(){
        if(mInstance==null){
            mInstance = new RxTest();
        }
        return mInstance;
    }
    public void post(String s){
        mSubject.onNext(s);
    }
    public Observable<String> getObservable(){return mSubject;}
}
