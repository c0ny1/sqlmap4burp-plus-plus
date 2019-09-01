package burp;

import java.io.PrintWriter;

public class BurpExtender implements IBurpExtender {
    public static IExtensionHelpers helpers;
    public static IBurpExtenderCallbacks callbacks;
    public static PrintWriter stdout;
    public static PrintWriter stderr;

    @Override
    public void registerExtenderCallbacks(final IBurpExtenderCallbacks callbacks) {
        this.helpers = callbacks.getHelpers();
        this.callbacks = callbacks;
        this.stdout = new PrintWriter(callbacks.getStdout(),true);
        this.stderr = new PrintWriter(callbacks.getStderr(),true);

        callbacks.registerContextMenuFactory(new Menu());
        callbacks.setExtensionName(String.format("%s %s",Config.getExtenderName(),Config.getExtenderVersion()));
        stdout.println(Util.getBanner());
    }
}
