package test;

import org.testng.annotations.BeforeMethod;
import org.testng.internal.annotations.ITest;

import java.lang.reflect.Method;

/**
 * Created by SvetLana on 01.07.2017.
 */
public abstract class TestNameSetter implements ITest {
    private String newTestName = "";

    public void setTestName(String newTestName){
        this.newTestName = newTestName;
    }

    public String getTestName() {

        return newTestName;
    }


    @BeforeMethod(alwaysRun=true)
    public void getTheNameFromParemeters(Method method, Object [] parameters){
        SetTestName setTestName = method.getAnnotation(SetTestName.class);
        String testCaseName = (String) parameters[setTestName.idx()];
        setTestName(testCaseName);
    }
}