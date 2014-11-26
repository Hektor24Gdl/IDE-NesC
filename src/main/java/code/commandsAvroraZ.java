/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import static code.TabPanel.EditButtonPro;
import static code.TabPanel.currentConfig;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Orlando
 */
public class commandsAvroraZ extends javax.swing.JDialog {

    /**
     * Creates new form commandsAvroraZ2
     */
    public commandsAvroraZ(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        //Customizing row Action
        this.cbAction.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("action")).get1() );
        actionComboBox.removeAllItems();
        for (String input : (String[]) ((Triplet)currentConfig.avroraZCommands.get("action")).get2()) {
            this.actionComboBox.addItem(input);
        }
        EditButtonPro(this.btnHelpAction, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetAction, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Banner
        this.cbBanner.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get1() );
        this.rbFBanner.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get2()));
        this.rbTBanner.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get2());
        ButtonGroup gBanner = new ButtonGroup();
        gBanner.add(this.rbFBanner);
        gBanner.add(this.rbTBanner);
        EditButtonPro(this.btnHelpBanner, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetBanner, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Colors
        this.cbColors.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get1() );
        this.rbFColors.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get2()));
        this.rbTColors.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get2());
        ButtonGroup gColors = new ButtonGroup();
        gColors.add(this.rbFColors);
        gColors.add(this.rbTColors);
        EditButtonPro(this.btnHelpColors, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetColors, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Html
        this.cbHtml.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get1() );
        this.rbFHtml.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get2()));
        this.rbTHtml.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get2());
        ButtonGroup gHtml = new ButtonGroup();
        gHtml.add(this.rbFHtml);
        gHtml.add(this.rbTHtml);
        EditButtonPro(this.btnHelpHtml, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetHtml, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Input
        this.cbInput.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("input")).get1() );

        this.cboxInput.removeAllItems();
        //some.cboxInput = new JComboBox(inputs);
        for (String input : (String[]) ((Triplet)currentConfig.avroraZCommands.get("input")).get3()) {
            this.cboxInput.addItem(input);
        }
        this.cboxInput.setSelectedIndex((int)((Triplet)currentConfig.avroraZCommands.get("input")).get2());
        EditButtonPro(this.btnHelpInput, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetInput, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row License
        this.cbLicense.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get1() );
        this.rbFLicense.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get2()));
        this.rbTLicense.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get2());
        ButtonGroup gLicense = new ButtonGroup();
        gLicense.add(this.rbFLicense);
        gLicense.add(this.rbTLicense);
        EditButtonPro(this.btnHelpLicense, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetLicense, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Status
        this.cbStatus.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get1() );
        this.rbFStatus.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get2()));
        this.rbTStatus.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get2());
        ButtonGroup gStatus = new ButtonGroup();
        gStatus.add(this.rbFStatus);
        gStatus.add(this.rbTStatus);
        EditButtonPro(this.btnHelpStatus, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetStatus, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Verbose
        this.cbVerbose.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("verbose")).get1() );
        this.txtVerbose.setText((String) ((Triplet)currentConfig.avroraZCommands.get("verbose")).get2() );
        EditButtonPro(this.btnHelpVerbose, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetVerbose, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row ConfigFile
        this.cbConfigFile.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("configFile")).get1() );
        this.txtConfigFile.setText((String) ((Triplet)currentConfig.avroraZCommands.get("configFile")).get2() );
        EditButtonPro(this.btnHelpConfigFile, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetConfigFile, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Extra
        this.cbExtra.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("extra")).get1() );
        this.txtExtra.setText((String) ((Triplet)currentConfig.avroraZCommands.get("extra")).get2() );
        EditButtonPro(this.btnHelpExtra, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetExtra, null, "/images/Eraser_24x24.png", "Reset");
        
        
        this.pack();
        this.setSize(730, 550);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cboxInput = new javax.swing.JComboBox();
        cbInput = new javax.swing.JCheckBox();
        btnResetInput = new javax.swing.JButton();
        rbFLicense = new javax.swing.JRadioButton();
        cbLicense = new javax.swing.JCheckBox();
        btnHelpLicense = new javax.swing.JButton();
        btnResetLicense = new javax.swing.JButton();
        btnResetStatus = new javax.swing.JButton();
        btnHelpStatus = new javax.swing.JButton();
        rbTLicense = new javax.swing.JRadioButton();
        rbFStatus = new javax.swing.JRadioButton();
        btnHelpConfigFile = new javax.swing.JButton();
        cbColors = new javax.swing.JCheckBox();
        txtConfigFile = new javax.swing.JTextField();
        btnResetConfigFile = new javax.swing.JButton();
        cbConfigFile = new javax.swing.JCheckBox();
        btnResetHtml = new javax.swing.JButton();
        rbFHtml = new javax.swing.JRadioButton();
        btnHelpHtml = new javax.swing.JButton();
        rbTHtml = new javax.swing.JRadioButton();
        cbHtml = new javax.swing.JCheckBox();
        btnHelpInput = new javax.swing.JButton();
        btnResetBanner = new javax.swing.JButton();
        cbBanner = new javax.swing.JCheckBox();
        btnHelpBanner = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        rbTColors = new javax.swing.JRadioButton();
        btnHelpColors = new javax.swing.JButton();
        rbFColors = new javax.swing.JRadioButton();
        btnResetColors = new javax.swing.JButton();
        btnResetExtra = new javax.swing.JButton();
        rbTBanner = new javax.swing.JRadioButton();
        rbFBanner = new javax.swing.JRadioButton();
        btnEditConfigFile = new javax.swing.JButton();
        btnHelpExtra = new javax.swing.JButton();
        cbAction = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtExtra = new javax.swing.JTextArea();
        cbExtra = new javax.swing.JCheckBox();
        btnOpenConfigFile = new javax.swing.JButton();
        txtVerbose = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnHelpVerbose = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnResetVerbose = new javax.swing.JButton();
        cbVerbose = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnHelpAction = new javax.swing.JButton();
        rbTStatus = new javax.swing.JRadioButton();
        btnResetAction = new javax.swing.JButton();
        cbStatus = new javax.swing.JCheckBox();
        actionComboBox = new javax.swing.JComboBox();
        tfMonitors = new javax.swing.JTextField();
        tfPlatform = new javax.swing.JTextField();
        tfTopology = new javax.swing.JTextField();
        tfNoise = new javax.swing.JTextField();
        cbMonitors = new javax.swing.JCheckBox();
        cbPlatform = new javax.swing.JCheckBox();
        cbTopology = new javax.swing.JCheckBox();
        cbNoise = new javax.swing.JCheckBox();
        cbUpdateNodeId = new javax.swing.JCheckBox();
        cbStaggerStart = new javax.swing.JCheckBox();
        cbReportSeconds = new javax.swing.JCheckBox();
        cbRealTime = new javax.swing.JCheckBox();
        cbSecondsPrecision = new javax.swing.JCheckBox();
        cbSeconds = new javax.swing.JCheckBox();
        cbSimulation = new javax.swing.JCheckBox();
        cbNodecount = new javax.swing.JCheckBox();
        rbTUpdateNodeId = new javax.swing.JRadioButton();
        rbFUpdateNodeId = new javax.swing.JRadioButton();
        tfStaggerStart = new javax.swing.JTextField();
        tfSecondsPrecision = new javax.swing.JTextField();
        tfSeconds = new javax.swing.JTextField();
        tfSimulation = new javax.swing.JTextField();
        tfNodecount = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 429));

        cboxInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbInput.setText("-input:");

        btnResetInput.setText(" ");
        btnResetInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInputActionPerformed(evt);
            }
        });

        rbFLicense.setText("False");

        cbLicense.setText("-license:");

        btnHelpLicense.setText(" ");

        btnResetLicense.setText(" ");
        btnResetLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetLicenseActionPerformed(evt);
            }
        });

        btnResetStatus.setText(" ");
        btnResetStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetStatusActionPerformed(evt);
            }
        });

        btnHelpStatus.setText(" ");

        rbTLicense.setSelected(true);
        rbTLicense.setText("True");

        rbFStatus.setText("False");

        btnHelpConfigFile.setText(" ");

        cbColors.setText("-colors:");

        txtConfigFile.setText("\\configurations\\default.txt");

        btnResetConfigFile.setText(" ");
        btnResetConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetConfigFileActionPerformed(evt);
            }
        });

        cbConfigFile.setSelected(true);
        cbConfigFile.setText("-config-file:");

        btnResetHtml.setText(" ");
        btnResetHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetHtmlActionPerformed(evt);
            }
        });

        rbFHtml.setText("False");

        btnHelpHtml.setText(" ");

        rbTHtml.setSelected(true);
        rbTHtml.setText("True");

        cbHtml.setText("-html:");

        btnHelpInput.setText(" ");

        btnResetBanner.setText(" ");
        btnResetBanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetBannerActionPerformed(evt);
            }
        });

        cbBanner.setText("-banner:");

        btnHelpBanner.setText(" ");

        jLabel5.setText("Help");

        rbTColors.setSelected(true);
        rbTColors.setText("True");

        btnHelpColors.setText(" ");

        rbFColors.setText("False");

        btnResetColors.setText(" ");
        btnResetColors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetColorsActionPerformed(evt);
            }
        });

        btnResetExtra.setText(" ");
        btnResetExtra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetExtraActionPerformed(evt);
            }
        });

        rbTBanner.setSelected(true);
        rbTBanner.setText("True");

        rbFBanner.setText("False");

        btnEditConfigFile.setText("Edit");
        btnEditConfigFile.setToolTipText("Open Config File");
        btnEditConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditConfigFileActionPerformed(evt);
            }
        });

        btnHelpExtra.setText(" ");

        cbAction.setText("-action:");

        txtExtra.setColumns(20);
        txtExtra.setRows(5);
        jScrollPane1.setViewportView(txtExtra);

        cbExtra.setSelected(true);
        cbExtra.setText("Extra:");

        btnOpenConfigFile.setText("...");
        btnOpenConfigFile.setToolTipText("Open Config File");
        btnOpenConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenConfigFileActionPerformed(evt);
            }
        });

        btnHelpVerbose.setText(" ");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel1.setText("AvroraZ commands");

        btnResetVerbose.setText(" ");
        btnResetVerbose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetVerboseActionPerformed(evt);
            }
        });

        cbVerbose.setText("-verbose:");

        jLabel2.setText("Command");

        jLabel3.setText("Value");

        jLabel4.setText("Reset");

        btnHelpAction.setText(" ");
        btnHelpAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpActionActionPerformed(evt);
            }
        });

        rbTStatus.setSelected(true);
        rbTStatus.setText("True");

        btnResetAction.setText(" ");
        btnResetAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionActionPerformed(evt);
            }
        });

        cbStatus.setText("-status:");

        actionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMonitors.setText("monitors");

        cbPlatform.setText("platform");

        cbTopology.setText("topology");

        cbNoise.setText("noise");

        cbUpdateNodeId.setText("update-node-id");

        cbStaggerStart.setText("stagger-start");

        cbReportSeconds.setText("report-seconds");

        cbRealTime.setText("real-time");

        cbSecondsPrecision.setText("seconds-precision");

        cbSeconds.setText("seconds");

        cbSimulation.setText("simulation");

        cbNodecount.setText("nodecount");

        rbTUpdateNodeId.setSelected(true);
        rbTUpdateNodeId.setText("True");

        rbFUpdateNodeId.setText("False");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnResetLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnHelpLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(btnResetStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnHelpStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(btnResetVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(18, 18, 18)
                                            .addComponent(btnHelpVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(btnResetColors, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnResetBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnResetAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(btnHelpBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnHelpAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(btnHelpColors, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnResetHtml, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpHtml, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnResetInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnResetExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHelpExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbExtra)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(66, 66, 66)
                                .addComponent(jLabel3))
                            .addComponent(cbVerbose)
                            .addComponent(cbLicense)
                            .addComponent(cbInput)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbConfigFile)
                                    .addComponent(cbMonitors)
                                    .addComponent(cbPlatform)
                                    .addComponent(cbTopology)
                                    .addComponent(cbNoise)
                                    .addComponent(cbUpdateNodeId)
                                    .addComponent(cbStaggerStart))
                                .addGap(28, 28, 28)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(txtVerbose, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)
                                            .addGap(252, 252, 252)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(tfMonitors)
                                                    .addComponent(tfPlatform)
                                                    .addComponent(tfNoise)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(rbTUpdateNodeId)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(rbFUpdateNodeId))
                                                    .addComponent(tfStaggerStart, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                                                    .addComponent(tfTopology))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(cbReportSeconds)
                                                    .addComponent(cbRealTime)
                                                    .addComponent(cbSecondsPrecision)
                                                    .addComponent(cbSeconds)
                                                    .addComponent(cbSimulation)
                                                    .addComponent(cbNodecount))))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnOpenConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnEditConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnResetConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(tfSecondsPrecision)
                                            .addComponent(tfSeconds)
                                            .addComponent(tfSimulation)
                                            .addComponent(tfNodecount))
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHelpConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbColors)
                                    .addComponent(cbBanner)
                                    .addComponent(cbAction)
                                    .addComponent(cbStatus)
                                    .addComponent(cbHtml))
                                .addGap(49, 49, 49)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbTLicense)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbFLicense))
                                    .addComponent(cboxInput, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbTStatus)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbFStatus))
                                    .addComponent(actionComboBox, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rbTBanner)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbFBanner))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(rbTHtml)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbFHtml))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(rbTColors)
                                            .addGap(18, 18, 18)
                                            .addComponent(rbFColors))))))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetAction)
                            .addComponent(btnHelpAction))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetBanner)
                            .addComponent(btnHelpBanner))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetColors)
                            .addComponent(btnHelpColors))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetHtml)
                            .addComponent(btnHelpHtml))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetInput)
                            .addComponent(btnHelpInput))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetLicense)
                            .addComponent(btnHelpLicense))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetStatus)
                            .addComponent(btnHelpStatus))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetVerbose)
                            .addComponent(btnHelpVerbose))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetExtra)
                            .addComponent(btnHelpExtra))
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbAction)
                                .addGap(8, 8, 8)
                                .addComponent(cbBanner)
                                .addGap(8, 8, 8)
                                .addComponent(cbColors)
                                .addGap(8, 8, 8)
                                .addComponent(cbHtml))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(actionComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbFBanner)
                                    .addComponent(rbTBanner))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbTColors)
                                    .addComponent(rbFColors))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbTHtml)
                                    .addComponent(rbFHtml))))
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbInput)
                            .addComponent(cboxInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbLicense)
                            .addComponent(rbTLicense)
                            .addComponent(rbFLicense))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbStatus)
                            .addComponent(rbTStatus)
                            .addComponent(rbFStatus))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbVerbose)
                            .addComponent(txtVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbExtra)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addGap(56, 56, 56)))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbConfigFile)
                    .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOpenConfigFile)
                    .addComponent(btnEditConfigFile)
                    .addComponent(btnResetConfigFile)
                    .addComponent(btnHelpConfigFile))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfMonitors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMonitors)
                    .addComponent(cbReportSeconds))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbPlatform)
                    .addComponent(cbRealTime))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTopology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTopology)
                    .addComponent(cbSecondsPrecision)
                    .addComponent(tfSecondsPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNoise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbNoise)
                    .addComponent(cbSeconds)
                    .addComponent(tfSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbUpdateNodeId)
                    .addComponent(cbSimulation)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbFUpdateNodeId)
                        .addComponent(rbTUpdateNodeId))
                    .addComponent(tfSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbStaggerStart)
                    .addComponent(cbNodecount)
                    .addComponent(tfStaggerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfNodecount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 7, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEditConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditConfigFileActionPerformed
        // TODO add your handling code here:
        //configFileCommands window = new configFileCommands(some.txtConfigFile.getText(),this,true);
        JPanel component = new JPanel();

        component.setLayout(null);

        JPanel header = new JPanel();
        header.setVisible(true);
        header.setLayout(null);
        JLabel title = new JLabel("Config File");
        title.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        title.setVisible(true);
        title.setBounds(10,0,150,30);
        header.add(title);

        JSeparator s = new JSeparator(SwingConstants.HORIZONTAL);
        s.setBounds(0,30,500,5);
        header.add(s);

        JLabel title1 = new JLabel("Command");
        title1.setVisible(true);
        title1.setBounds(20,30,150,30);
        header.add(title1);

        JLabel title2 = new JLabel("Value");
        title2.setVisible(true);
        title2.setBounds(160,30,150,30);
        header.add(title2);

        JSeparator s2 = new JSeparator(SwingConstants.HORIZONTAL);
        s2.setBounds(0,55,500,5);
        header.add(s2);
        header.setBounds(0,5,500,60);
        component.add(header);

        FileManagement fileManagement = new FileManagement();
        String fileConfig = fileManagement.readFile(new File(this.txtConfigFile.getText()));
        String[] lines = fileConfig.split("\n");
        int a=10,b=10+60,d=40;

        JTextArea ta =  new JTextArea();
        ta.setVisible(true);
        ta.setBounds(10,10,250,100);
        
        for(String line : lines){
            if(!line.startsWith("#")){
                String[] params = line.split("=");
                JPanel pn = new JPanel();
                pn.setVisible(true);
                pn.setLayout(null);

                if(params.length == 2){

                    JCheckBox label = new JCheckBox(params[0]);
                    label.setVisible(true);
                    label.setSelected(true);
                    label.setBounds(10,10,150,30);

                    JTextField text = new JTextField(params[1]);
                    text.setVisible(true);
                    text.setBounds(150,10,250,30);

                    pn.setBounds(a,b,400,d);
                    b = b + 35;
                    pn.add(label);
                    pn.add(text);
                    component.add(pn);
                }
                else{
                    String param = (!"".equals(ta.getText())) ? ta.getText() + "\n" + params[0] : params[0];
                    ta.setText(param);
                }
            }
        }
        JPanel pn = new JPanel();
        pn.setVisible(true);
        pn.setLayout(null);
        pn.setBounds(a,b+10,400,120);

        JCheckBox label = new JCheckBox("Extra");
        label.setVisible(true);
        label.setSelected(true);
        label.setBounds(10,10,150,40);
        pn.add(label);

        JScrollPane sp = new JScrollPane(ta);
        sp.setVisible(true);
        sp.setBounds(150,10,250,100);
        pn.add(sp);

        component.add(pn);
        
        JDialog dialog2 = new JDialog();
        dialog2.setSize(500, 650);
        dialog2.setModal(true);
        dialog2.setTitle("Edit the AvroraZ Config File");
        dialog2.setResizable(false);
        dialog2.setLayout(null);

        component.setPreferredSize(new Dimension(480,b+150));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 500, 650);
        scrollPane.getViewport().add(component);
        scrollPane.setPreferredSize(new Dimension(500,650));
        dialog2.getContentPane().add(scrollPane, BorderLayout.CENTER);

        
        //Opening in the center of the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();  
        Dimension screenSize = toolkit.getScreenSize(); 
        int x = (screenSize.width - dialog2.getWidth()) / 2;  
        int y = (screenSize.height - dialog2.getHeight()) / 2;
        dialog2.setLocation(x, y);
        //Make dialog visible
        dialog2.setVisible(true);
    }//GEN-LAST:event_btnEditConfigFileActionPerformed

    private void btnOpenConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenConfigFileActionPerformed
        // TODO add your handling code here:
        JFileChooser configFileChooser = new JFileChooser();
        configFileChooser.setFileFilter(new FileNameExtensionFilter("AvroraZ Config (*.txt)", "txt"));
        if (configFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            this.txtConfigFile.setText(configFileChooser.getSelectedFile().getPath());
        }
    }//GEN-LAST:event_btnOpenConfigFileActionPerformed

    private void btnHelpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btnHelpActionActionPerformed

    private void btnResetActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionActionPerformed
        // TODO add your handling code here:
        this.cbAction.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("action")).get1() );
        actionComboBox.setSelectedItem("simulate");
    }//GEN-LAST:event_btnResetActionActionPerformed

    private void btnResetBannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBannerActionPerformed
        // TODO add your handling code here:
        this.cbBanner.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get1() );
        this.rbFBanner.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get2()));
        this.rbTBanner.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("banner")).get2());
    }//GEN-LAST:event_btnResetBannerActionPerformed

    private void btnResetColorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetColorsActionPerformed
        // TODO add your handling code here:
        this.cbColors.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get1() );
        this.rbFColors.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get2()));
        this.rbTColors.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("colors")).get2());
    }//GEN-LAST:event_btnResetColorsActionPerformed

    private void btnResetHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetHtmlActionPerformed
        // TODO add your handling code here:
        this.cbHtml.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get1() );
        this.rbFHtml.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get2()));
        this.rbTHtml.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("html")).get2());
    }//GEN-LAST:event_btnResetHtmlActionPerformed

    private void btnResetInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInputActionPerformed
        // TODO add your handling code here:
        this.cbInput.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("input")).get1() );

        this.cboxInput.removeAllItems();
        //some.cboxInput = new JComboBox(inputs);
        for (String input : (String[]) ((Triplet)currentConfig.avroraZCommands.get("input")).get3()) {
            this.cboxInput.addItem(input);
        }
        this.cboxInput.setSelectedIndex((int)((Triplet)currentConfig.avroraZCommands.get("input")).get2());
    }//GEN-LAST:event_btnResetInputActionPerformed

    private void btnResetLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLicenseActionPerformed
        // TODO add your handling code here:
        this.cbLicense.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get1() );
        this.rbFLicense.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get2()));
        this.rbTLicense.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("license")).get2());
    }//GEN-LAST:event_btnResetLicenseActionPerformed

    private void btnResetStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetStatusActionPerformed
        // TODO add your handling code here:
        this.cbStatus.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get1() );
        this.rbFStatus.setSelected(!((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get2()));
        this.rbTStatus.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("status")).get2());
    }//GEN-LAST:event_btnResetStatusActionPerformed

    private void btnResetVerboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetVerboseActionPerformed
        // TODO add your handling code here:
        this.cbVerbose.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("verbose")).get1() );
        this.txtVerbose.setText((String) ((Triplet)currentConfig.avroraZCommands.get("verbose")).get2() );
    }//GEN-LAST:event_btnResetVerboseActionPerformed

    private void btnResetConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetConfigFileActionPerformed
        // TODO add your handling code here:
        this.cbConfigFile.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("configFile")).get1() );
        this.txtConfigFile.setText((String) ((Triplet)currentConfig.avroraZCommands.get("configFile")).get2() );
    }//GEN-LAST:event_btnResetConfigFileActionPerformed

    private void btnResetExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetExtraActionPerformed
        // TODO add your handling code here:
        this.cbExtra.setSelected((boolean) ((Triplet)currentConfig.avroraZCommands.get("extra")).get1() );
        this.txtExtra.setText((String) ((Triplet)currentConfig.avroraZCommands.get("extra")).get2() );
    }//GEN-LAST:event_btnResetExtraActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(commandsAvroraZ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(commandsAvroraZ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(commandsAvroraZ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(commandsAvroraZ.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                commandsAvroraZ dialog = new commandsAvroraZ(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox actionComboBox;
    private javax.swing.JButton btnEditConfigFile;
    private javax.swing.JButton btnHelpAction;
    private javax.swing.JButton btnHelpBanner;
    private javax.swing.JButton btnHelpColors;
    private javax.swing.JButton btnHelpConfigFile;
    private javax.swing.JButton btnHelpExtra;
    private javax.swing.JButton btnHelpHtml;
    private javax.swing.JButton btnHelpInput;
    private javax.swing.JButton btnHelpLicense;
    private javax.swing.JButton btnHelpStatus;
    private javax.swing.JButton btnHelpVerbose;
    private javax.swing.JButton btnOpenConfigFile;
    private javax.swing.JButton btnResetAction;
    private javax.swing.JButton btnResetBanner;
    private javax.swing.JButton btnResetColors;
    private javax.swing.JButton btnResetConfigFile;
    private javax.swing.JButton btnResetExtra;
    private javax.swing.JButton btnResetHtml;
    private javax.swing.JButton btnResetInput;
    private javax.swing.JButton btnResetLicense;
    private javax.swing.JButton btnResetStatus;
    private javax.swing.JButton btnResetVerbose;
    private javax.swing.JCheckBox cbAction;
    private javax.swing.JCheckBox cbBanner;
    private javax.swing.JCheckBox cbColors;
    private javax.swing.JCheckBox cbConfigFile;
    private javax.swing.JCheckBox cbExtra;
    private javax.swing.JCheckBox cbHtml;
    private javax.swing.JCheckBox cbInput;
    private javax.swing.JCheckBox cbLicense;
    private javax.swing.JCheckBox cbMonitors;
    private javax.swing.JCheckBox cbNodecount;
    private javax.swing.JCheckBox cbNoise;
    private javax.swing.JCheckBox cbPlatform;
    private javax.swing.JCheckBox cbRealTime;
    private javax.swing.JCheckBox cbReportSeconds;
    private javax.swing.JCheckBox cbSeconds;
    private javax.swing.JCheckBox cbSecondsPrecision;
    private javax.swing.JCheckBox cbSimulation;
    private javax.swing.JCheckBox cbStaggerStart;
    private javax.swing.JCheckBox cbStatus;
    private javax.swing.JCheckBox cbTopology;
    private javax.swing.JCheckBox cbUpdateNodeId;
    private javax.swing.JCheckBox cbVerbose;
    private javax.swing.JComboBox cboxInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JRadioButton rbFBanner;
    private javax.swing.JRadioButton rbFColors;
    private javax.swing.JRadioButton rbFHtml;
    private javax.swing.JRadioButton rbFLicense;
    private javax.swing.JRadioButton rbFStatus;
    private javax.swing.JRadioButton rbFUpdateNodeId;
    private javax.swing.JRadioButton rbTBanner;
    private javax.swing.JRadioButton rbTColors;
    private javax.swing.JRadioButton rbTHtml;
    private javax.swing.JRadioButton rbTLicense;
    private javax.swing.JRadioButton rbTStatus;
    private javax.swing.JRadioButton rbTUpdateNodeId;
    private javax.swing.JTextField tfMonitors;
    private javax.swing.JTextField tfNodecount;
    private javax.swing.JTextField tfNoise;
    private javax.swing.JTextField tfPlatform;
    private javax.swing.JTextField tfSeconds;
    private javax.swing.JTextField tfSecondsPrecision;
    private javax.swing.JTextField tfSimulation;
    private javax.swing.JTextField tfStaggerStart;
    private javax.swing.JTextField tfTopology;
    private javax.swing.JTextField txtConfigFile;
    private javax.swing.JTextArea txtExtra;
    private javax.swing.JTextField txtVerbose;
    // End of variables declaration//GEN-END:variables
}
