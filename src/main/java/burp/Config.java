package burp;

public class Config {
    private static final String EXTENDER_NAME = "sqlmap4burp++";
    private static final String EXTENDER_VERSION = "0.1";
    private static String PYTHON_NAME = "python";
    private static String SQLMAP_PATH = "sqlmap";
    private static String REQUST_FILE_PATH = "";
    private static String SQLMAP_OPTIONS_COMMAND = "";
    private static String OS_TYPE;
    private static boolean IS_INJECT = false;


    public static String getExtenderName() {
        return EXTENDER_NAME;
    }

    public static String getExtenderVersion() {
        return EXTENDER_VERSION;
    }

    public static String getPythonName() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("PYTHON_NAME");
            if(val == null){
                return Config.PYTHON_NAME;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.PYTHON_NAME;
        }
    }

    public static void setPythonName(String pythonName) {
        BurpExtender.callbacks.saveExtensionSetting("PYTHON_NAME", String.valueOf(pythonName));
        Config.SQLMAP_PATH = pythonName;
    }

    public static String getSqlmapPath() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("SQLMAP_PATH");
            if(val == null){
                return Config.SQLMAP_PATH;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.SQLMAP_PATH;
        }
    }

    public static void setSqlmapPath(String sqlmapPath) {
        BurpExtender.callbacks.saveExtensionSetting("SQLMAP_PATH", String.valueOf(sqlmapPath));
        Config.SQLMAP_PATH = sqlmapPath;
    }

    public static String getRequstFilePath() {
        return REQUST_FILE_PATH;
    }

    public static void setRequstFilePath(String requstFilePath) {
        REQUST_FILE_PATH = requstFilePath;
    }

    public static String getSqlmapOptionsCommand() {
        try {
            String val = BurpExtender.callbacks.loadExtensionSetting("SQLMAP_OPTIONS_COMMAND");
            if(val == null){
                return Config.SQLMAP_OPTIONS_COMMAND;
            }else{
                return val;
            }
        }catch(Exception e){
            return Config.SQLMAP_OPTIONS_COMMAND;
        }
    }

    public static void setSqlmapOptionsCommand(String sqlmapOptionsCommand) {
        BurpExtender.callbacks.saveExtensionSetting("SQLMAP_OPTIONS_COMMAND", String.valueOf(sqlmapOptionsCommand));
        Config.SQLMAP_OPTIONS_COMMAND = sqlmapOptionsCommand;
    }

    public static String getOsType() {
        return OS_TYPE;
    }

    public static void setOsType(String osType) {
        OS_TYPE = osType;
    }

    public static boolean isIsInject() {
        return IS_INJECT;
    }

    public static void setIsInject(boolean isInject) {
        IS_INJECT = isInject;
    }
}
