package environment;

import org.testng.IExecutionListener;

public class WebPhases implements IExecutionListener {

    private String env = null;

    @Override
    public void onExecutionStart() {
        preEnvironmentSetup();
    }

    @Override
    public void onExecutionFinish() {
    }

    private void preEnvironmentSetup() {
        env = System.getProperty("environment");
        if (System.getProperty("environment") == null) {
            EnvironmentHandler.getInstance();
        } else {
            EnvironmentHandler.getInstance(EnvironmentType.valueOf(env));
        }
    }
}
