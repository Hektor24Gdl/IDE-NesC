/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code;

import static code.TabPanel.EditButtonPro;
import static code.TabPanel.currentConfig;
import exceptions.InvalidFileExeption;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Orlando
 */
public class commandsAvroraZ extends javax.swing.JDialog {

    private Help help;
    private final ResourceBundle bundle;

    /**
     * Creates new form commandsAvroraZ2
     * @param parent the parent frame
     * @param modal open as modal
     */
    public commandsAvroraZ(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        initComponents();

        bundle = java.util.ResourceBundle.getBundle("help.avroraz_commands");
        cbInput.setToolTipText(bundle.getString("input_short")); // NOI18N
        cbLicense.setToolTipText(bundle.getString("license_short")); // NOI18N
        cbColors.setToolTipText(bundle.getString("colors_short")); // NOI18N
        cbHtml.setToolTipText(bundle.getString("html_short")); // NOI18N
        cbBanner.setToolTipText(bundle.getString("banner_short")); // NOI18N
        cbAction.setToolTipText(bundle.getString("action_short")); // NOI18N
        cbVerbose.setToolTipText(bundle.getString("verbose_short")); // NOI18N
        cbStatus.setToolTipText(bundle.getString("status_short")); // NOI18N
        cbMonitors.setToolTipText(bundle.getString("monitors_short")); // NOI18N

        //Customizing row Action
        this.cbAction.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("action")).get1());
        cboxAction.removeAllItems();
        for (String input : (String[]) ((Triplet) currentConfig.avroraZCommands.get("action")).get2()) {
            this.cboxAction.addItem(input);
        }
        EditButtonPro(this.btnHelpAction, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetAction, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Banner
        this.cbBanner.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get1());
        this.rbFBanner.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get2()));
        this.rbTBanner.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get2());
        ButtonGroup gBanner = new ButtonGroup();
        gBanner.add(this.rbFBanner);
        gBanner.add(this.rbTBanner);
        EditButtonPro(this.btnHelpBanner, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetBanner, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Colors
        this.cbColors.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get1());
        this.rbFColors.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get2()));
        this.rbTColors.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get2());
        ButtonGroup gColors = new ButtonGroup();
        gColors.add(this.rbFColors);
        gColors.add(this.rbTColors);
        EditButtonPro(this.btnHelpColors, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetColors, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Html
        this.cbHtml.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get1());
        this.rbFHtml.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get2()));
        this.rbTHtml.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get2());
        ButtonGroup gHtml = new ButtonGroup();
        gHtml.add(this.rbFHtml);
        gHtml.add(this.rbTHtml);
        EditButtonPro(this.btnHelpHtml, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetHtml, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Input
        this.cbInput.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("input")).get1());

        this.cboxInput.removeAllItems();
        //some.cboxInput = new JComboBox(inputs);
        for (String input : (String[]) ((Triplet) currentConfig.avroraZCommands.get("input")).get3()) {
            this.cboxInput.addItem(input);
        }
        this.cboxInput.setSelectedIndex((int) ((Triplet) currentConfig.avroraZCommands.get("input")).get2());
        EditButtonPro(this.btnHelpInput, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetInput, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row License
        this.cbLicense.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get1());
        this.rbFLicense.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get2()));
        this.rbTLicense.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get2());
        ButtonGroup gLicense = new ButtonGroup();
        gLicense.add(this.rbFLicense);
        gLicense.add(this.rbTLicense);
        EditButtonPro(this.btnHelpLicense, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetLicense, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Status
        this.cbStatus.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get1());
        this.rbFStatus.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get2()));
        this.rbTStatus.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get2());
        ButtonGroup gStatus = new ButtonGroup();
        gStatus.add(this.rbFStatus);
        gStatus.add(this.rbTStatus);
        EditButtonPro(this.btnHelpStatus, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetStatus, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row Verbose
        this.cbVerbose.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("verbose")).get1());
        this.tfVerbose.setText((String) ((Triplet) currentConfig.avroraZCommands.get("verbose")).get2());
        EditButtonPro(this.btnHelpVerbose, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetVerbose, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing row ConfigFile
        this.txtConfigFile.setText(null);
        EditButtonPro(this.btnHelpConfigFile, null, "/images/Help_24x24.png", "Help");
        EditButtonPro(this.btnResetConfigFile, null, "/images/Eraser_24x24.png", "Reset");
        //Customizing output file
        this.tfOutput.setText((String) ((Triplet) currentConfig.avroraZCommands.get("output")).get2());

        //Customizing row Extra
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
        btnHelpExtra = new javax.swing.JButton();
        cbAction = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfExtra = new javax.swing.JTextArea();
        cbCommand = new javax.swing.JCheckBox();
        btnOpenConfigFile = new javax.swing.JButton();
        tfVerbose = new javax.swing.JTextField();
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
        cboxAction = new javax.swing.JComboBox();
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
        lbConfigFile = new javax.swing.JLabel();
        lbOutput = new javax.swing.JLabel();
        tfOutput = new javax.swing.JTextField();
        btRun = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(938, 692));
        setResizable(false);

        cboxInput.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxInput.setName("cboxInput"); // NOI18N

        cbInput.setText("-input:");
        cbInput.setName("cbInput"); // NOI18N

        btnResetInput.setText(" ");
        btnResetInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetInputActionPerformed(evt);
            }
        });

        rbFLicense.setText("False");
        rbFLicense.setName("rbFLicense"); // NOI18N

        cbLicense.setText("-license:");
        cbLicense.setName("cbLicense"); // NOI18N

        btnHelpLicense.setText(" ");
        btnHelpLicense.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpLicenseActionPerformed(evt);
            }
        });

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
        btnHelpStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpStatusActionPerformed(evt);
            }
        });

        rbTLicense.setSelected(true);
        rbTLicense.setText("True");
        rbTLicense.setName("rbTLicense"); // NOI18N

        rbFStatus.setText("False");
        rbFStatus.setName("rbFStatus"); // NOI18N

        btnHelpConfigFile.setText(" ");

        cbColors.setText("-colors:");
        cbColors.setName("cbColors"); // NOI18N

        btnResetConfigFile.setText(" ");
        btnResetConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetConfigFileActionPerformed(evt);
            }
        });

        btnResetHtml.setText(" ");
        btnResetHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetHtmlActionPerformed(evt);
            }
        });

        rbFHtml.setText("False");
        rbFHtml.setName("rbFHtml"); // NOI18N

        btnHelpHtml.setText(" ");
        btnHelpHtml.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpHtmlActionPerformed(evt);
            }
        });

        rbTHtml.setSelected(true);
        rbTHtml.setText("True");
        rbTHtml.setName("rbTHtml"); // NOI18N

        cbHtml.setText("-html:");
        cbHtml.setName("cbHtml"); // NOI18N

        btnHelpInput.setText(" ");
        btnHelpInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpInputActionPerformed(evt);
            }
        });

        btnResetBanner.setText(" ");
        btnResetBanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetBannerActionPerformed(evt);
            }
        });

        cbBanner.setText("-banner:");
        cbBanner.setName("cbBanner"); // NOI18N

        btnHelpBanner.setText(" ");
        btnHelpBanner.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpBannerActionPerformed(evt);
            }
        });

        jLabel5.setText("Help");

        rbTColors.setSelected(true);
        rbTColors.setText("True");
        rbTColors.setName("rbTColors"); // NOI18N

        btnHelpColors.setText(" ");
        btnHelpColors.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpColorsActionPerformed(evt);
            }
        });

        rbFColors.setText("False");
        rbFColors.setName("rbFColors"); // NOI18N

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
        rbTBanner.setName("rbTBanner"); // NOI18N

        rbFBanner.setText("False");
        rbFBanner.setName("rbFBanner"); // NOI18N

        btnHelpExtra.setText(" ");

        cbAction.setText("-action:");
        cbAction.setName("cbAction"); // NOI18N

        tfExtra.setColumns(20);
        tfExtra.setRows(5);
        tfExtra.setName("tfExtra"); // NOI18N
        jScrollPane1.setViewportView(tfExtra);

        cbCommand.setSelected(true);
        cbCommand.setText("command");
        cbCommand.setName("cbCommand"); // NOI18N

        btnOpenConfigFile.setText("...");
        btnOpenConfigFile.setToolTipText("Open Config File");
        btnOpenConfigFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenConfigFileActionPerformed(evt);
            }
        });

        tfVerbose.setName("tfVerbose"); // NOI18N

        btnHelpVerbose.setText(" ");
        btnHelpVerbose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHelpVerboseActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 16)); // NOI18N
        jLabel1.setText("AvroraZ commands");

        btnResetVerbose.setText(" ");
        btnResetVerbose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetVerboseActionPerformed(evt);
            }
        });

        cbVerbose.setText("-verbose:");
        cbVerbose.setName("cbVerbose"); // NOI18N

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
        rbTStatus.setName("rbTStatus"); // NOI18N

        btnResetAction.setText(" ");
        btnResetAction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionActionPerformed(evt);
            }
        });

        cbStatus.setText("-status:");
        cbStatus.setName("cbStatus"); // NOI18N

        cboxAction.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboxAction.setName("cboxAction"); // NOI18N

        tfMonitors.setName("tfMonitors"); // NOI18N

        tfPlatform.setName("tfPlatform"); // NOI18N

        tfTopology.setName("tfTopology"); // NOI18N

        tfNoise.setName("tfNoise"); // NOI18N

        cbMonitors.setText("monitors");
        cbMonitors.setName("cbMonitors"); // NOI18N

        cbPlatform.setText("platform");
        cbPlatform.setName("cbPlatform"); // NOI18N

        cbTopology.setText("topology");
        cbTopology.setName("cbTopology"); // NOI18N

        cbNoise.setText("noise");
        cbNoise.setName("cbNoise"); // NOI18N

        cbUpdateNodeId.setText("update-node-id");
        cbUpdateNodeId.setName("cbUpdateNodeId"); // NOI18N

        cbStaggerStart.setText("stagger-start");
        cbStaggerStart.setName("cbStaggerStart"); // NOI18N

        cbReportSeconds.setText("report-seconds");
        cbReportSeconds.setName("cbReportSeconds"); // NOI18N

        cbRealTime.setText("real-time");
        cbRealTime.setName("cbRealTime"); // NOI18N

        cbSecondsPrecision.setText("seconds-precision");
        cbSecondsPrecision.setName("cbSecondsPrecision"); // NOI18N

        cbSeconds.setText("seconds");
        cbSeconds.setName("cbSeconds"); // NOI18N

        cbSimulation.setText("simulation");
        cbSimulation.setName("cbSimulation"); // NOI18N

        cbNodecount.setText("nodecount");
        cbNodecount.setName("cbNodecount"); // NOI18N

        rbTUpdateNodeId.setSelected(true);
        rbTUpdateNodeId.setText("True");
        rbTUpdateNodeId.setName("rbTUpdateNodeId"); // NOI18N

        rbFUpdateNodeId.setText("False");
        rbFUpdateNodeId.setName("rbFUpdateNodeId"); // NOI18N

        tfStaggerStart.setName("tfStaggerStart"); // NOI18N

        tfSecondsPrecision.setName("tfSecondsPrecision"); // NOI18N

        tfSeconds.setName("tfSeconds"); // NOI18N

        tfSimulation.setName("tfSimulation"); // NOI18N

        tfNodecount.setName("tfNodecount"); // NOI18N

        lbConfigFile.setText("config-file");

        lbOutput.setText("output file");

        btRun.setText("Run");
        btRun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRunActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(102, 102, 102)
                                                .addComponent(cboxAction, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(rbFColors)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(cbBanner)
                                                    .addGap(29, 29, 29)
                                                    .addComponent(rbTBanner)
                                                    .addGap(23, 23, 23)
                                                    .addComponent(rbFBanner))
                                                .addComponent(rbFHtml)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel4)
                                            .addGap(2, 2, 2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(58, 58, 58)
                                            .addComponent(jLabel5)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(btnResetBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnResetAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnResetColors, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnHelpBanner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnHelpAction, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnHelpColors, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbInput)
                                    .addComponent(cbLicense)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbColors)
                                            .addComponent(cbHtml))
                                        .addGap(34, 34, 34)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(rbTColors)
                                            .addComponent(rbTHtml))))
                                .addGap(212, 212, 212)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(17, 17, 17)
                                                .addComponent(btnResetLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpLicense, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(btnResetInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(66, 66, 66)
                                        .addComponent(btnResetHtml, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHelpHtml, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbStatus)
                                    .addComponent(cbVerbose))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnResetStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHelpStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnResetVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnHelpVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addGap(66, 66, 66)
                                        .addComponent(jLabel3))
                                    .addComponent(cbAction)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboxInput, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbTLicense)
                                                    .addComponent(rbTStatus))
                                                .addGap(26, 26, 26)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(rbFStatus)
                                                    .addComponent(rbFLicense)))
                                            .addComponent(tfVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbCommand)
                                    .addComponent(lbOutput)
                                    .addComponent(lbConfigFile))
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnResetConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnResetExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(btnHelpExtra, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addComponent(tfOutput, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cbSimulation)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tfSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cbReportSeconds)
                                            .addComponent(cbRealTime)
                                            .addComponent(cbSecondsPrecision)
                                            .addComponent(cbSeconds))
                                        .addGap(26, 26, 26)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(tfSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(tfSecondsPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbMonitors)
                                                .addComponent(cbPlatform)
                                                .addComponent(cbTopology)
                                                .addComponent(cbNoise)
                                                .addComponent(cbUpdateNodeId)
                                                .addComponent(cbStaggerStart))
                                            .addGap(45, 45, 45)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(rbTUpdateNodeId)
                                                    .addGap(18, 18, 18)
                                                    .addComponent(rbFUpdateNodeId))
                                                .addComponent(tfStaggerStart, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfNoise, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfTopology, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tfMonitors, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addComponent(cbNodecount)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(btRun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(tfNodecount, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)))))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnOpenConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfMonitors, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbMonitors))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfPlatform, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbPlatform))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfTopology, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbTopology))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfNoise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbNoise))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbUpdateNodeId)
                            .addComponent(rbFUpdateNodeId)
                            .addComponent(rbTUpdateNodeId))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbStaggerStart)
                            .addComponent(tfStaggerStart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(cbReportSeconds)
                        .addGap(18, 18, 18)
                        .addComponent(cbRealTime)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSecondsPrecision)
                            .addComponent(tfSecondsPrecision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSeconds)
                            .addComponent(tfSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbSimulation)
                            .addComponent(tfSimulation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbNodecount)
                            .addComponent(tfNodecount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(202, 202, 202))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnResetAction)
                                    .addComponent(btnHelpAction)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbAction))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(cboxAction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHelpBanner)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnResetBanner)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(rbFBanner)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(cbBanner)
                                            .addComponent(rbTBanner))))
                                .addGap(21, 21, 21)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(rbTColors)
                                    .addComponent(rbFColors)
                                    .addComponent(cbColors)
                                    .addComponent(btnResetColors)
                                    .addComponent(btnHelpColors))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnHelpHtml)
                                    .addComponent(btnResetHtml)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rbTHtml)
                                        .addComponent(rbFHtml))
                                    .addComponent(cbHtml))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetInput)
                            .addComponent(btnHelpInput)
                            .addComponent(cbInput)
                            .addComponent(cboxInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetLicense)
                            .addComponent(btnHelpLicense)
                            .addComponent(cbLicense)
                            .addComponent(rbTLicense)
                            .addComponent(rbFLicense))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetStatus)
                            .addComponent(btnHelpStatus)
                            .addComponent(cbStatus)
                            .addComponent(rbTStatus)
                            .addComponent(rbFStatus))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnResetVerbose)
                            .addComponent(btnHelpVerbose)
                            .addComponent(cbVerbose)
                            .addComponent(tfVerbose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnResetExtra)
                                .addComponent(btnHelpExtra))
                            .addComponent(cbCommand)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbOutput)
                            .addComponent(tfOutput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbConfigFile)
                            .addComponent(txtConfigFile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnResetConfigFile)
                            .addComponent(btnHelpConfigFile)
                            .addComponent(btnOpenConfigFile)
                            .addComponent(btRun))
                        .addGap(117, 117, 117))))
        );

        cboxInput.getAccessibleContext().setAccessibleName("cboxInput");
        rbFLicense.getAccessibleContext().setAccessibleName("rbFLicense");
        rbTLicense.getAccessibleContext().setAccessibleName("rbTLicense");
        rbFStatus.getAccessibleContext().setAccessibleName("rbFStatus");
        rbFHtml.getAccessibleContext().setAccessibleName("rbFHtml");
        rbTHtml.getAccessibleContext().setAccessibleName("rbTHtml");
        rbTColors.getAccessibleContext().setAccessibleName("rbTColors");
        rbFColors.getAccessibleContext().setAccessibleName("rbFColors");
        rbTBanner.getAccessibleContext().setAccessibleName("rbTBanner");
        rbFBanner.getAccessibleContext().setAccessibleName("rbFBanner");
        cbCommand.getAccessibleContext().setAccessibleName("cbCommand");
        rbTStatus.getAccessibleContext().setAccessibleName("rbTStatus");
        cboxAction.getAccessibleContext().setAccessibleName("cboxAction");
        lbOutput.getAccessibleContext().setAccessibleName("lbOutput");
        tfOutput.getAccessibleContext().setAccessibleName("tfOutput");
        btRun.getAccessibleContext().setAccessibleName("btRun");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenConfigFileActionPerformed
        // TODO add your handling code here:
        JFileChooser configFileChooser = new JFileChooser();
        configFileChooser.setFileFilter(new FileNameExtensionFilter("AvroraZ Config (*.txt)", "txt"));
        if (configFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                this.txtConfigFile.setText(configFileChooser.getSelectedFile().getPath());
                
                //Get file info
                FileManagement fileManagement = new FileManagement();
                String fileConfig = fileManagement.readFile(new File(this.txtConfigFile.getText()));
                String[] lines = fileConfig.split("\n");
                
                Component[] components = this.getContentPane().getComponents();
                for (String line : lines) {
                    if (!line.startsWith("#")) {
                        String[] params = line.split("=");
                        JPanel pn = new JPanel();
                        pn.setVisible(true);
                        pn.setLayout(null);
                        
                        if (params.length == 2) {
                            if (params[0].equals("update-node-id")) {
                                if (params[1].equalsIgnoreCase("true")) {
                                    rbTUpdateNodeId.setSelected(true);
                                } else {
                                    rbTUpdateNodeId.setSelected(false);
                                }
                            }
                            for (Component component : components) {
                                if (component.getName() != null && component.getName().equalsIgnoreCase("tf" + params[0].replace("-", ""))) {
                                    JTextField aux = (JTextField) component;
                                    aux.setText(params[1]);
                                }
                            }
                        }
                    }
                }
            } catch (InvalidFileExeption ex) {
                Logger.getLogger(commandsAvroraZ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnOpenConfigFileActionPerformed

    private void btnHelpActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpActionActionPerformed
        showHelp("action", bundle.getString("action"));
    }//GEN-LAST:event_btnHelpActionActionPerformed

    private void btnResetActionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionActionPerformed
        // TODO add your handling code here:
        this.cbAction.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("action")).get1());
        cboxAction.setSelectedItem("simulate");
    }//GEN-LAST:event_btnResetActionActionPerformed

    private void btnResetBannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetBannerActionPerformed
        // TODO add your handling code here:
        this.cbBanner.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get1());
        this.rbFBanner.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get2()));
        this.rbTBanner.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("banner")).get2());
    }//GEN-LAST:event_btnResetBannerActionPerformed

    private void btnResetColorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetColorsActionPerformed
        // TODO add your handling code here:
        this.cbColors.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get1());
        this.rbFColors.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get2()));
        this.rbTColors.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("colors")).get2());
    }//GEN-LAST:event_btnResetColorsActionPerformed

    private void btnResetHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetHtmlActionPerformed
        // TODO add your handling code here:
        this.cbHtml.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get1());
        this.rbFHtml.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get2()));
        this.rbTHtml.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("html")).get2());
    }//GEN-LAST:event_btnResetHtmlActionPerformed

    private void btnResetInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetInputActionPerformed
        // TODO add your handling code here:
        this.cbInput.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("input")).get1());

        this.cboxInput.removeAllItems();
        //some.cboxInput = new JComboBox(inputs);
        for (String input : (String[]) ((Triplet) currentConfig.avroraZCommands.get("input")).get3()) {
            this.cboxInput.addItem(input);
        }
        this.cboxInput.setSelectedIndex((int) ((Triplet) currentConfig.avroraZCommands.get("input")).get2());
    }//GEN-LAST:event_btnResetInputActionPerformed

    private void btnResetLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetLicenseActionPerformed
        // TODO add your handling code here:
        this.cbLicense.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get1());
        this.rbFLicense.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get2()));
        this.rbTLicense.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("license")).get2());
    }//GEN-LAST:event_btnResetLicenseActionPerformed

    private void btnResetStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetStatusActionPerformed
        // TODO add your handling code here:
        this.cbStatus.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get1());
        this.rbFStatus.setSelected(!((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get2()));
        this.rbTStatus.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("status")).get2());
    }//GEN-LAST:event_btnResetStatusActionPerformed

    private void btnResetVerboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetVerboseActionPerformed
        // TODO add your handling code here:
        this.cbVerbose.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("verbose")).get1());
        this.tfVerbose.setText((String) ((Triplet) currentConfig.avroraZCommands.get("verbose")).get2());
    }//GEN-LAST:event_btnResetVerboseActionPerformed

    private void btnResetConfigFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetConfigFileActionPerformed
        // TODO add your handling code here:
        this.txtConfigFile.setText(null);
    }//GEN-LAST:event_btnResetConfigFileActionPerformed

    private void btnResetExtraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetExtraActionPerformed
        // TODO add your handling code here:
        this.cbCommand.setSelected((boolean) ((Triplet) currentConfig.avroraZCommands.get("extra")).get1());
        this.tfExtra.setText((String) ((Triplet) currentConfig.avroraZCommands.get("extra")).get2());
    }//GEN-LAST:event_btnResetExtraActionPerformed

    private void btnHelpBannerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpBannerActionPerformed
        showHelp("banner", bundle.getString("banner"));
    }//GEN-LAST:event_btnHelpBannerActionPerformed

    private void btnHelpColorsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpColorsActionPerformed
        showHelp("colors", bundle.getString("colors"));
    }//GEN-LAST:event_btnHelpColorsActionPerformed

    private void btnHelpHtmlActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpHtmlActionPerformed
        showHelp("html", bundle.getString("html"));
    }//GEN-LAST:event_btnHelpHtmlActionPerformed

    private void btnHelpInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpInputActionPerformed
        showHelp("input", bundle.getString("input"));
    }//GEN-LAST:event_btnHelpInputActionPerformed

    private void btnHelpLicenseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpLicenseActionPerformed
        showHelp("license", bundle.getString("license"));
    }//GEN-LAST:event_btnHelpLicenseActionPerformed

    private void btnHelpStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpStatusActionPerformed
        showHelp("status", bundle.getString("status"));
    }//GEN-LAST:event_btnHelpStatusActionPerformed

    private void btnHelpVerboseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHelpVerboseActionPerformed
        showHelp("verbose", bundle.getString("verbose"));
    }//GEN-LAST:event_btnHelpVerboseActionPerformed

    private void btRunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRunActionPerformed
        Component[] components = this.getContentPane().getComponents();
        List<String> commands = new ArrayList<>();
        File file = new File("/home/hector/TinyOSCodeSample/SimpleAppC.nc");
        String workingDir = file.getParentFile().getAbsolutePath();
        String urlSimulator = "/home/hector/Dropbox/IDE_nesC/Documentacion/Investigacion IDE-NesC/Info_Alejandro/avroraz.jar";
        commands.add("java");
        commands.add("-jar");
        commands.add(urlSimulator);
        commands.add(workingDir + "/build/objdump/main.od");
        for (Component component : components) {
            if (component.getName() != null && component.getName().contains("cb") && component instanceof JCheckBox) {
                JCheckBox cb = (JCheckBox) component;
                if(cb.isSelected() && !cb.getName().equals("cbCommand")){
                    List<String> data = getType(cb.getName());
                    if(data.size()== 1){
                        commands.add(data.get(0));
                    }else{
                        String type = data.get(0);
                        switch(type){
                            case "cbox":
                                JComboBox cbox = (JComboBox) findComponent("cbox" + component.getName().substring(2));
                                commands.add(data.get(1) + "=" + cbox.getSelectedItem().toString());
                                break;
                            case "rb":
                                JRadioButton rb = (JRadioButton) findComponent("rbT" + component.getName().substring(2));
                                String result = rb.isSelected() ? "true" : "false";
                                commands.add(data.get(1) + "="  + result);                                
                                break;
                            case "tf":
                                JTextField tf = (JTextField) findComponent("tf" + component.getName().substring(2));
                                commands.add(data.get(1) + "=" + tf.getText());
                                break;
                        }
                    }
                }
            }
        }
        JCheckBox ccb = (JCheckBox) findComponent("cbCommand");
        if(ccb.isSelected()){
            JTextArea ctf = (JTextArea) findComponent("tfExtra");
            commands.add(ctf.getText());
        }
        simulate(workingDir, commands);
    }//GEN-LAST:event_btRunActionPerformed

    private Component findComponent(String name){
        Component [] components = this.getContentPane().getComponents();
        for(Component component : components){
            if(component.getName() != null && component.getName().equalsIgnoreCase(name))
                return component;
        }
        return null;
    }
    
    private void simulate(String workingDir, List<String> command){
        Runtime runtime = Runtime.getRuntime();
        try {
            String createDir = String.format("mkdir %s/build/avroraz", workingDir);
            Process createDirProcess = runtime.exec(createDir);
            createDirProcess.waitFor();
            Thread.sleep(200);
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            //Java Process doesn't support the ">" redirect as bash shell does. So, ProcessBuilder is needed
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectOutput(new File(workingDir + "/build/avroraz/main.txt"));
            Process commandProcess = pb.start();
            commandProcess.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Retorna una lista con el tipo de componente que contiene la información
     * de entrada que el usuario introdujo en la GUI y el nombre del comando para 
     * avroraz.
     * cbox: javax.swing.JComboBox
     * rb: javax.swing.JRadioButton
     * tf: javax.swing.JTextField
     * @return La lista con la información
     */
    private List<String> getType(String cbName){
        List<String> data = new ArrayList<>();
        switch(cbName){
            case "cbAction":
                data.add("cbox");
                data.add("-action");
                break;
            case "cbBanner":
                data.add("rb");
                data.add("-banner");
                break;
            case "cbColors":
                data.add("rb");
                data.add("-colors");
                break;
            case "cbHtml":
                data.add("rb");
                data.add("-html");
                break;
            case "cbInput":
                data.add("cbox");
                data.add("-input");
                break;
            case "cbLicense":
                data.add("rb");
                data.add("-license");
                break;
            case "cbStatus":
                data.add("rb");
                data.add("-status");
                break;
            case "cbVerbose":
                data.add("tf");
                data.add("-verbose");
                break;
            case "cbMonitors":
                data.add("tf");
                data.add("-monitors");
                break;
            case "cbPlatform":
                data.add("tf");
                data.add("-platform");
                break;
            case "cbTopology":
                data.add("tf");
                data.add("-topology");
                break;
            case "cbNoise":
                data.add("tf");
                data.add("-noise");
                break;
            case "cbUpdateNodeId":
                data.add("rb");
                data.add("-update-node-id");
                break;
            case "cbStaggerStart":
                data.add("tf");
                data.add("-stagger-start");
                break;
            case "cbReportSeconds":
                data.add("-report-seconds");
                break;
            case "cbRealTime":
                data.add("-real-time");
                break;
            case "cbSecondsPrecision":
                data.add("tf");
                data.add("-seconds-precision");
                break;
            case "cbSeconds":
                data.add("tf");
                data.add("-seconds");
                break;
            case "cbSimulation":
                data.add("tf");
                data.add("-simulation");
                break;
            case "cbNodecount":
                data.add("tf");
                data.add("-nodecount");
                break; 
        }
        return data;
    }
    
    private void showHelp(String function, String helpText) {
        if (help == null) {
            help = new Help((java.awt.Frame) this.getParent(), true);
        }
        
        help.setHelp(function, helpText);

        help.setEnabled(true);
        help.setVisible(true);
    }

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
    private javax.swing.JButton btRun;
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
    private javax.swing.JCheckBox cbCommand;
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
    private javax.swing.JComboBox cboxAction;
    private javax.swing.JComboBox cboxInput;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbConfigFile;
    private javax.swing.JLabel lbOutput;
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
    private javax.swing.JTextArea tfExtra;
    private javax.swing.JTextField tfMonitors;
    private javax.swing.JTextField tfNodecount;
    private javax.swing.JTextField tfNoise;
    private javax.swing.JTextField tfOutput;
    private javax.swing.JTextField tfPlatform;
    private javax.swing.JTextField tfSeconds;
    private javax.swing.JTextField tfSecondsPrecision;
    private javax.swing.JTextField tfSimulation;
    private javax.swing.JTextField tfStaggerStart;
    private javax.swing.JTextField tfTopology;
    private javax.swing.JTextField tfVerbose;
    private javax.swing.JTextField txtConfigFile;
    // End of variables declaration//GEN-END:variables
}
