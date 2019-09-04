package burp;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Menu implements IContextMenuFactory {

    @Override
    public List<JMenuItem> createMenuItems(final IContextMenuInvocation invocation) {
        List<JMenuItem> list = new ArrayList<JMenuItem>();

//        if(invocation.getInvocationContext() != IContextMenuInvocation.CONTEXT_MESSAGE_EDITOR_REQUEST){
//            return list;
//        }

        JMenuItem jMenuItem = new JMenuItem("Send to sqlmap4burp++");
        list.add(jMenuItem);
        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConfigDlg cfd = new ConfigDlg();
                cfd.show();

                if(Config.isIsInject()) {
                    IHttpRequestResponse[] messages = invocation.getSelectedMessages();
                    byte[] req = messages[0].getRequest();
                    IHttpService httpService = messages[0].getHttpService();
                    String host = httpService.getHost().replace(".", "_");
                    int port = httpService.getPort();

                    SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                    String data = df.format(new Date());
                    String requstFilename = String.format("%s_%s_%s.req", host, port, data);
                    String reqFilePath = Util.getTempReqName(requstFilename);
                    Util.writeFile(req, reqFilePath);
                    new Thread(new SqlmapStarter()).start();
                    Config.setIsInject(false);
                }
            }
        });
        return list;
    }
}
