package burp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 配置窗口类，负责显示配置窗口，处理窗口消息
 */
public class ConfigDlg extends JDialog {
    private final JPanel mainPanel = new JPanel();

    private final JLabel lbPythonName =  new JLabel("Python name:");
    private final JTextField tfPythonName = new JTextField(30);
    private final JLabel lbSqlmapPath = new JLabel("Sqlmap path:");
    private final JTextField tfSqlmapPath = new JTextField(30);
    private final JButton btnBrowse = new JButton("Browse");
    private final JLabel lbSqlmapOption = new JLabel("Sqlmap option:");
    private final JTextField tfSqlmapOption = new JTextField(30);
    private final JLabel lbPrompt = new JLabel("Prompt:");

    private final JButton btnOK = new JButton("OK");
    private final JButton btnCancel = new JButton("Cancel");


    public ConfigDlg(){
        initGUI();
        initEvent();
        initValue();
        this.setTitle("sqlmap4burp++ config");
    }


    /**
     * 初始化UI
     */
    private void initGUI(){
        JLabel lbPythonNameHelp = new JLabel("?");
        lbPythonNameHelp.setToolTipText("eg: python,python2,python3,py2,py3,...");
        JLabel lbSqlmapOptionHelp = new JLabel("?");
        lbSqlmapOptionHelp.setToolTipText("eg: --level 5,--batch,...");

        mainPanel.setLayout(new GridBagLayout());
        mainPanel.add(lbPythonName,new GBC(0,0,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
        mainPanel.add(tfPythonName, new GBC(2,0,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(lbPythonNameHelp,new GBC(5,0,6,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(lbSqlmapPath,new GBC(0,1,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
        mainPanel.add(tfSqlmapPath,new GBC(2,1,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(btnBrowse,new GBC(5,1,1,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(lbSqlmapOption,new GBC(0,2,2,1).setFill(GBC.BOTH).setInsets(10,10,2,0));
        mainPanel.add(tfSqlmapOption,new GBC(2,2,3,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(lbSqlmapOptionHelp,new GBC(5,2,1,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        mainPanel.add(btnOK,new GBC(0,3,1,1).setFill(GBC.BOTH).setInsets(10,10,10,0));
        mainPanel.add(btnCancel,new GBC(1,3,1,1).setFill(GBC.BOTH).setInsets(10,0,10,10));

        if(Util.getOSType() == Util.OS_LINUX){
            lbPrompt.setText("Notice: The command will be copied to the clipboard. Paste it into Terminal!");
            mainPanel.add(lbPrompt,new GBC(2,3,1,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        }else if(Util.getOSType() == Util.OS_MAC){
            lbPrompt.setText("Notice: Please ensure that Terminal is in running state!");
            mainPanel.add(lbPrompt,new GBC(2,3,1,1).setFill(GBC.BOTH).setInsets(10,0,2,10));
        }
        lbPrompt.setForeground(new Color(0,0,255));

        this.setModal(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.add(mainPanel);
        //使配置窗口自动适应控件大小，防止部分控件无法显示
        this.pack();
        //居中显示配置窗口
        Dimension screensize=Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(screensize.width/2-this.getWidth()/2,screensize.height/2-this.getHeight()/2,this.getWidth(),this.getHeight());
        BurpExtender.callbacks.customizeUiComponent(this);
    }


    /**
     * 初始化事件
     */
    private void initEvent(){

        btnBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);//设置只能选择目录
                int returnVal = chooser.showOpenDialog(ConfigDlg.this);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    String selectPath =chooser.getSelectedFile().getPath() ;
                    tfSqlmapPath.setText(selectPath);
                    chooser.hide();
                }
            }
        });


        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setIsInject(true);
                Config.setPythonName(tfPythonName.getText().trim());
                Config.setSqlmapPath(tfSqlmapPath.getText().trim());
                Config.setSqlmapOptionsCommand(tfSqlmapOption.getText().trim());
                ConfigDlg.this.dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Config.setIsInject(false);
                ConfigDlg.this.dispose();
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Config.setIsInject(false);
            }
        });

    }


    /**
     * 为控件赋值
     */
    public void initValue(){
        tfPythonName.setText(Config.getPythonName());
        BurpExtender.stderr.println("Python name:"+Config.getPythonName());
        tfSqlmapPath.setText(Config.getSqlmapPath());
        tfSqlmapOption.setText(Config.getSqlmapOptionsCommand());
    }
}