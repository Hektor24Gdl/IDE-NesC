package code;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.accessibility.Accessible;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 * Class of the main interface.
 */
public class TabPanel extends javax.swing.JFrame implements Accessible {

    static int tabCounter = 1;
    final static TabPanel some = new TabPanel();
    static FileManagement fileManagement = new FileManagement();
    private static HashMap hmAreas = new HashMap();
    public static CurrentConfig currentConfig = new CurrentConfig();
    private static DocumentNC doc;
    private static Console console;
    private Object areaTexto;

    /**
     * Creates new form TabPanel
     */
    public TabPanel() {
        initComponents();
        this.console = new Console(this.jtpConsole);
        try{
            ActionMap actions = this.jtpConsole.getActionMap();
            final JPopupMenu popUpMenu = new JPopupMenu();
            final JMenuItem copyItem, clearItem;
            JSeparator separador =new JSeparator();

            copyItem = new JMenuItem();
            copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
            copyItem.setAction(actions.get(DefaultEditorKit.copyAction));
            copyItem.setText("Copy");
            copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
            popUpMenu.add(copyItem); 
            
            popUpMenu.add(separador);
            /*
            cutItem=new JMenuItem();
            cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
            cutItem.setAction(actions.get(DefaultEditorKit.cutAction));
            cutItem.setText("Cut");
            cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
            popUpMenu.add(cutItem);         
            
            popUpMenu.add(separador);
            
            pasteItem = new JMenuItem();
            pasteItem.setAction(actions.get(DefaultEditorKit.pasteAction));
            pasteItem.setText("Paste");
            pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
            popUpMenu.add(pasteItem);*/
            clearItem = new JMenuItem();
            //clearItem.setAction(actions.get(DefaultEditorKit.pasteAction));
            clearItem.setText("Clear");
            //clearItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
            clearItem.addMouseListener(new MouseListener() {
                @Override
                public void mousePressed(MouseEvent e) {
                    // TODO Auto-generated method stub
                    jtpConsole.setText("");
                } 

                @Override
                public void mouseClicked(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
        
             });
            popUpMenu.add(clearItem);
            
            this.jtpConsole.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ev){
                   boolean textoSeleccionado = jtpConsole.getSelectedText()!=null;
                    //cutItem.setEnabled(textoSeleccionado);
                    //cutItem.setEnabled(false);
                   // pasteItem.setEnabled(false);
                    copyItem.setEnabled(textoSeleccionado);
                    if(ev.getButton()==MouseEvent.BUTTON3)
                       popUpMenu.show(jtpConsole,ev.getX(),ev.getY());   
                }
            });

        }catch( Exception ex){
           // some.console.writeUnexpectedError( ex.toString() );
        }

    }

    /**
     * Function that return a personalized button
     *
     * @param iconPath
     * @param toolTip
     * @return button
     * @throws IOException
     */
    public static JButton ButtonPro(String iconPath, String toolTip) throws IOException {
        ImageIcon icon = createImageIcon(iconPath);
        final JButton b = new JButton(icon);
        final Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
        final Insets insets = raisedBevelBorder.getBorderInsets(b);
        final EmptyBorder emptyBorder = new EmptyBorder(insets);
        b.setBorder(emptyBorder);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setToolTipText(toolTip);
        b.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    b.setBorder(raisedBevelBorder);
                } else {
                    b.setBorder(emptyBorder);
                }
            }
        });
        return b;
    }

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     *
     * @param file
     * @return
     * @throws java.io.IOException
     */
    protected static ImageIcon createImageIcon(String file) throws IOException {
        ImageIcon imgi = new ImageIcon(TabPanel.class.getResource(file));
        if (imgi != null) {
            return imgi;
        } else {
            System.err.println("Couldn't find file: " + file);
            return null;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        info = new javax.swing.JToolBar();
        label1 = new java.awt.Label();
        jToolBar1 = new javax.swing.JToolBar();
        btnNew = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        btnSave = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnSaveAll = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnCompile = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnConvert = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        btnAvroraz = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnMakeAll = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btCheck = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpConsole = new javax.swing.JTextPane();
        tabs = new javax.swing.JTabbedPane();
        menu = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        New = new javax.swing.JMenuItem();
        save = new javax.swing.JMenuItem();
        saveAs = new javax.swing.JMenuItem();
        saveAll = new javax.swing.JMenuItem();
        open = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        close = new javax.swing.JMenuItem();
        run = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        info.setRollover(true);

        label1.setText("Version 1.0");
        info.add(label1);

        jToolBar1.setFloatable(false);

        btnNew.setMaximumSize(new java.awt.Dimension(32, 32));
        btnNew.setMinimumSize(new java.awt.Dimension(32, 32));
        btnNew.setPreferredSize(new java.awt.Dimension(32, 32));
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNew);

        jLabel1.setText("   ");
        jToolBar1.add(jLabel1);

        btnOpen.setMaximumSize(new java.awt.Dimension(32, 32));
        btnOpen.setMinimumSize(new java.awt.Dimension(32, 32));
        btnOpen.setPreferredSize(new java.awt.Dimension(32, 32));
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOpen);

        jLabel2.setText("   ");
        jToolBar1.add(jLabel2);

        btnSave.setMaximumSize(new java.awt.Dimension(32, 32));
        btnSave.setMinimumSize(new java.awt.Dimension(32, 32));
        btnSave.setPreferredSize(new java.awt.Dimension(32, 32));
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSave);

        jLabel3.setText("   ");
        jToolBar1.add(jLabel3);

        btnSaveAll.setFocusable(false);
        btnSaveAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnSaveAll.setMaximumSize(new java.awt.Dimension(32, 32));
        btnSaveAll.setMinimumSize(new java.awt.Dimension(32, 32));
        btnSaveAll.setPreferredSize(new java.awt.Dimension(32, 32));
        btnSaveAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnSaveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveAllActionPerformed(evt);
            }
        });
        jToolBar1.add(btnSaveAll);

        jSeparator3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jSeparator3.setPreferredSize(new java.awt.Dimension(30, 30));
        jSeparator3.setSeparatorSize(new java.awt.Dimension(30, 30));
        jToolBar1.add(jSeparator3);

        btnCompile.setFocusable(false);
        btnCompile.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnCompile.setMaximumSize(new java.awt.Dimension(32, 32));
        btnCompile.setMinimumSize(new java.awt.Dimension(32, 32));
        btnCompile.setPreferredSize(new java.awt.Dimension(32, 32));
        btnCompile.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnCompile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompileActionPerformed(evt);
            }
        });
        jToolBar1.add(btnCompile);

        jLabel4.setText("   ");
        jToolBar1.add(jLabel4);

        btnConvert.setFocusable(false);
        btnConvert.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnConvert.setMaximumSize(new java.awt.Dimension(32, 32));
        btnConvert.setMinimumSize(new java.awt.Dimension(32, 32));
        btnConvert.setPreferredSize(new java.awt.Dimension(32, 32));
        btnConvert.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnConvert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConvertActionPerformed(evt);
            }
        });
        jToolBar1.add(btnConvert);

        jLabel5.setText("   ");
        jToolBar1.add(jLabel5);

        btnAvroraz.setFocusable(false);
        btnAvroraz.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAvroraz.setMaximumSize(new java.awt.Dimension(32, 32));
        btnAvroraz.setMinimumSize(new java.awt.Dimension(32, 32));
        btnAvroraz.setPreferredSize(new java.awt.Dimension(32, 32));
        btnAvroraz.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAvroraz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAvrorazActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAvroraz);

        jSeparator4.setMaximumSize(new java.awt.Dimension(30, 30));
        jSeparator4.setMinimumSize(new java.awt.Dimension(30, 30));
        jSeparator4.setPreferredSize(new java.awt.Dimension(30, 30));
        jSeparator4.setRequestFocusEnabled(false);
        jSeparator4.setSeparatorSize(new java.awt.Dimension(30, 30));
        jToolBar1.add(jSeparator4);

        btnMakeAll.setFocusable(false);
        btnMakeAll.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnMakeAll.setMaximumSize(new java.awt.Dimension(32, 32));
        btnMakeAll.setMinimumSize(new java.awt.Dimension(32, 32));
        btnMakeAll.setPreferredSize(new java.awt.Dimension(32, 32));
        btnMakeAll.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnMakeAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMakeAllActionPerformed(evt);
            }
        });
        jToolBar1.add(btnMakeAll);
        jToolBar1.add(jSeparator5);

        btCheck.setFocusable(false);
        btCheck.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCheck.setLabel("Check");
        btCheck.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCheckActionPerformed(evt);
            }
        });
        jToolBar1.add(btCheck);
        btCheck.getAccessibleContext().setAccessibleName("btCheck");

        jSplitPane1.setDividerLocation(400);
        jSplitPane1.setDividerSize(13);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setResizeWeight(1.0);
        jSplitPane1.setOneTouchExpandable(true);

        jScrollPane1.setViewportView(jtpConsole);

        jSplitPane1.setRightComponent(jScrollPane1);
        jSplitPane1.setLeftComponent(tabs);

        file.setText("File");

        New.setText("New");
        New.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewActionPerformed(evt);
            }
        });
        file.add(New);

        save.setText("Save");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        file.add(save);

        saveAs.setText("Save As...");
        saveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAsActionPerformed(evt);
            }
        });
        file.add(saveAs);

        saveAll.setText("Save All");
        saveAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveAllActionPerformed(evt);
            }
        });
        file.add(saveAll);

        open.setText("Open");
        open.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openActionPerformed(evt);
            }
        });
        file.add(open);
        file.add(jSeparator1);

        close.setText("Close");
        file.add(close);

        menu.add(file);

        run.setText("Run");

        jMenuItem1.setText("Compile");
        run.add(jMenuItem1);

        jMenuItem2.setText("Compile All");
        run.add(jMenuItem2);

        jMenuItem3.setText("Mount");
        run.add(jMenuItem3);
        run.add(jSeparator2);

        jMenuItem4.setText("Options");
        run.add(jMenuItem4);

        menu.add(run);

        jMenu1.setText("Test");

        jMenuItem5.setText("O.K.");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem6.setText("Fail");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);

        menu.add(jMenu1);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(info, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(info, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        saveFile(false, some.tabs.getSelectedIndex());
    }//GEN-LAST:event_saveActionPerformed

    private void openActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openActionPerformed
        // TODO add your handling code here:
        openFile();
    }//GEN-LAST:event_openActionPerformed

    /**
     * Function that save changes in a file with the option as new file.
     *
     * @param as
     * @param selectedIndex
     */
    private void saveFile(boolean as, int selectedIndex) {
        // Create a file chooser

        final JFileChooser fileChooser = new JFileChooser();
        JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(selectedIndex);
        String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
        boolean flag = false;
        int selection = -1;
        String pathFile = null;
        fileChooser.setFileFilter(new FileNameExtensionFilter("NesC Files (*.nc)", "nc"));
        // Response to button click
        if (((Triplet) hmAreas.get(Integer.parseInt(s1))).get2() != null && !as) {
            flag = true;
            pathFile = (String) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get2();
        } else {
            selection = fileChooser.showSaveDialog(this);
        }
        if (selection == JFileChooser.APPROVE_OPTION || flag) {
            File file;
            if (selection == JFileChooser.APPROVE_OPTION) {
                file = fileChooser.getSelectedFile();
            } else {
                file = new File(pathFile);
            }
            try {
                fileManagement.saveFile(file.getPath(), ((JTextPane) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get3()).getText());
                ((Triplet) hmAreas.get(Integer.parseInt(s1))).set2(file.getPath());
                ((Triplet) hmAreas.get(Integer.parseInt(s1))).set1(true);
                ImageIcon iconjl = null;
                try {
                    iconjl = iconjl = createImageIcon("/images/saved.png");
                } catch (IOException ex) {
                    this.console.writeUnexpectedError(ex.getMessage());
                    Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                JPanel pnl = new JPanel();
                pnl.setOpaque(false);
                pnlTab.remove(0);
                pnlTab.remove(0);
                pnl.add(new JLabel(iconjl));
                pnl.add(new JLabel(file.getName()));
                pnl.add(pnlTab.getComponent(0));
                some.tabs.setTabComponentAt(some.tabs.getSelectedIndex(), pnl);

            } catch (IOException ex) {
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Function that open a existing file with a jFileChooser
     */
    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("od Files (*.od)", "od"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("NesC Files (*.nc)", "nc"));
        int selection = fileChooser.showOpenDialog(this);
        if (selection == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                newTab(some.tabs, file);
            } catch (IOException ex) {
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                this.console.writeUnexpectedError(ex.getMessage());
                Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Function that open a file
     * @param path
     */
    private void openFile(String path) {
        try {
            File file = new File(path);
            newTab(some.tabs,file );
            
        } catch (IOException ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*
     * Function that open a new tab with a blank document.
     * @param jTP
     * @throws Exception 
     */
   /* private static void newTab(JTabbedPane jTP) throws Exception {
        newTabb(jTP, );
    }*/
    
    /**
     * Function for the new file in the gui
     * @param evt 
     */
    private void NewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewActionPerformed
        try {
            newTab(some.tabs,new File(""));
        } catch (Exception ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_NewActionPerformed
    /**
     * Function that correspond to the new file in the menu. This function add a
     * new tab in the JTabPane
     * @param evt
     */
    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        // TODO add your handling code here:
        saveFile(false, some.tabs.getSelectedIndex());
    }//GEN-LAST:event_btnSaveActionPerformed

    /**
     * Function for the open a file in the gui
     * @param evt 
     */
    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        // TODO add your handling code here:
        openFile();
    }//GEN-LAST:event_btnOpenActionPerformed
    /**
     * Function that correspond to de new button. This function add a new tab in
     * the JTabPane
     *
     * @param evt
     */
    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        // TODO add your handling code here:
        try {
            newTab(some.tabs,new File(""));
        } catch (IOException ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    /**
     * Function for the save as option in the menu
     * @param evt 
     */
    private void saveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAsActionPerformed
        // TODO add your handling code here:
        saveFile(true, some.tabs.getSelectedIndex());
    }//GEN-LAST:event_saveAsActionPerformed

    /**
     * Function for the save all option in the menu
     * @param evt 
     */
    private void saveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveAllActionPerformed
        // TODO add your handling code here:
        saveAll();
    }//GEN-LAST:event_saveAllActionPerformed

    /**
     * Function for the save all button in the gui
     * @param evt 
     */
    private void btnSaveAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveAllActionPerformed
        // TODO add your handling code here:
        saveAll();
    }//GEN-LAST:event_btnSaveAllActionPerformed
    
    /**
     * Function that save all files opened 
     */
    private void saveAll(){
        for (int i = 0; i < some.tabs.getTabCount() - 1; i++) {
            some.tabs.setSelectedIndex(i);
            saveFile(false, i);
        }
    }
    /**
     * Function for the compile buton in the gui
     * @param evt 
     */
    private void btnCompileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompileActionPerformed
        //File file = new File("/home/linux/TinyOSCodeSample/SimpleAppC.nc");
        // compile(file.getParentFile().getAbsolutePath());
        
        
        JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
        String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
        if(!(Boolean)((Triplet) hmAreas.get(Integer.parseInt(s1))).get1()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save All Files First?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                this.saveAll();
            }
        }
        File aux = new File((String) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get2());
        compile(aux.getParentFile().getAbsolutePath());
    }//GEN-LAST:event_btnCompileActionPerformed

    /**
     * Function for the convert button in the gui
     * @param evt 
     */
    private void btnConvertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConvertActionPerformed
        //File file = new File("/home/linux/TinyOSCodeSample/SimpleAppC.nc");
        //convert(file.getParentFile().getAbsolutePath());
        
        JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
        String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
        if(!(Boolean)((Triplet) hmAreas.get(Integer.parseInt(s1))).get1()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save This Files First?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                this.saveFile(false, some.tabs.getSelectedIndex());
            }
        }
        File aux = new File((String) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get2());
        convert(aux.getParentFile().getAbsolutePath());
    }//GEN-LAST:event_btnConvertActionPerformed

    /**
     * Function for the avroraz button in the gui
     * @param evt 
     */
    private void btnAvrorazActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAvrorazActionPerformed
        // TODO add your handling code here:
        btnAvroraz();
    }//GEN-LAST:event_btnAvrorazActionPerformed

    /**
     * Function for the make all button in the gui
     * @param evt 
     */
    private void btnMakeAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMakeAllActionPerformed
        //File file = new File("/home/linux/TinyOSCodeSample/SimpleAppC.nc");
        //String workingDir = file.getParentFile().getAbsolutePath();
        //compile(workingDir);
        //convert(workingDir);
        JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
        String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
        
        if(!(Boolean)((Triplet) hmAreas.get(Integer.parseInt(s1))).get1()){
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Would You Like to Save All Files First?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                this.saveAll();
            }
        }
        File aux = new File((String) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get2());
        String workingDir = aux.getParentFile().getAbsolutePath();
        compile(workingDir);
        convert(workingDir);
    }//GEN-LAST:event_btnMakeAllActionPerformed

    /**
     * Function for the check button in the gui
     * @param evt 
     */
    private void btCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCheckActionPerformed
        // TODO add your handling code here:
        JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
        String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
        try {
            saveFile(false, some.tabs.getSelectedIndex());
            JTextPane textp = (JTextPane) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get3();

            textp = doc.lexer(new File((String) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get2()), (JTextPane) ((Triplet) hmAreas.get(Integer.parseInt(s1))).get3(), some.jtpConsole);

            some.pack();
            some.revalidate();
            some.repaint();
            some.setVisible(true);
        } catch (NumberFormatException | FileNotFoundException ex) {
            some.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            some.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btCheckActionPerformed

    /**
     * 
     * @param evt 
     */
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> files = new ArrayList<>();
        files.add("src/test/resources/avroraz/test.txt");
        files.add("src/test/resources/avroraz/test.txt");
        files.add("src/test/resources/avroraz/test.txt");
        files.add("src/test/resources/avroraz/test.txt");
        for (String file : files) {
            openFile(file);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        ArrayList<String> files = new ArrayList<>();
        files.add("src/test/resources/avroraz/invalidId.txt");
        files.add("src/test/resources/avroraz/invalidSquareBrackets.txt");
        files.add("src/test/resources/avroraz/invalidType.txt");
        files.add("src/test/resources/avroraz/invalidVariable.txt");
        for (String file : files) {
            openFile(file);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    /**
     * Function for compile nesc 
     * @param workingDir 
     */
    private void compile(String workingDir) {
        Runtime runtime = Runtime.getRuntime();
        ProcessBuilder pb = new ProcessBuilder("make", "-C", workingDir, "micaz");
        Process commandProcess = null;
        try {
            //Java Process doesn't support the ">" redirect as bash shell does. So, ProcessBuilder is needed
            commandProcess = pb.start();
            commandProcess.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(commandProcess.getErrorStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
               builder.append(line);
               builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            Color color;
            color = (commandProcess.exitValue()==0) ? Color.decode("0x04B404") : Color.RED;
            if("".equals(result) && commandProcess.exitValue()==0 ) result = "SUCCESS";
            this.console.write("  --Compiler >> " + result, color);
        } catch (IOException | InterruptedException ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        /**try {
            String command = String.format("make -C %s micaz", workingDir);
            System.out.println(command);
            Process process = runtime.exec(command);
            process.waitFor();
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }

    /**
     * Function for the conver function 
     * @param workingDir 
     */
    private void convert(String workingDir) {
        Runtime runtime = Runtime.getRuntime();
        try {
            String createDir = String.format("mkdir %s/build/objdump", workingDir);
            Process createDirProcess = runtime.exec(createDir);
            createDirProcess.waitFor();
            Thread.sleep(200);
            
        } catch (IOException | InterruptedException ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            //Java Process doesn't support the ">" redirect as bash shell does. So, ProcessBuilder is needed
            ProcessBuilder pb = new ProcessBuilder("avr-objdump", "-h", "-D", workingDir + "/build/micaz/main.exe");
            pb.redirectOutput(new File(workingDir + "/build/objdump/main.od"));
            Process commandProcess = pb.start();
            commandProcess.waitFor();
            
            BufferedReader reader = new BufferedReader(new InputStreamReader(commandProcess.getErrorStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ( (line = reader.readLine()) != null) {
               builder.append(line);
               builder.append(System.getProperty("line.separator"));
            }
            String result = builder.toString();
            Color color;
            color = (commandProcess.exitValue()==0) ? Color.decode("0x04B404") : Color.RED;
             if("".equals(result) && commandProcess.exitValue()==0 ) result = "SUCCESS";
            this.console.write("  --Converter >> " + result, color);
        } catch (IOException | InterruptedException ex) {
            this.console.writeUnexpectedError(ex.getMessage());
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function that add a tab in the main JTabPane.
     *
     * @param tp
     * @param file
     * @throws IOException
     * @throws Exception
     */
    static void newTab(final JTabbedPane tp, File file) throws IOException, Exception {
        //JEditorPane ep = new JEditorPane();
        //JTextArea ep = new JTextArea();
        final JTextPane ep = new JTextPane();
        try{
            ActionMap actions = ep.getActionMap();
            final JPopupMenu popUpMenu = new JPopupMenu();
            final JMenuItem cutItem,copyItem,pasteItem;
            JSeparator separador =new JSeparator();

            copyItem = new JMenuItem();
            copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
            copyItem.setAction(actions.get(DefaultEditorKit.copyAction));
            copyItem.setText("Copy");
            copyItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,InputEvent.CTRL_MASK));
            popUpMenu.add(copyItem); 
            
            popUpMenu.add(separador);
            
            cutItem=new JMenuItem();
            cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
            cutItem.setAction(actions.get(DefaultEditorKit.cutAction));
            cutItem.setText("Cut");
            cutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.CTRL_MASK));
            popUpMenu.add(cutItem);         
            
            popUpMenu.add(separador);
            
            pasteItem = new JMenuItem();
            pasteItem.setAction(actions.get(DefaultEditorKit.pasteAction));
            pasteItem.setText("Paste");
            pasteItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,InputEvent.CTRL_MASK));
            popUpMenu.add(pasteItem);    
            
            ep.addMouseListener(new MouseAdapter(){
                public void mousePressed(MouseEvent ev){
                   boolean textoSeleccionado = ep.getSelectedText()!=null;
                    cutItem.setEnabled(textoSeleccionado);
                    copyItem.setEnabled(textoSeleccionado);
                    if(ev.getButton()==MouseEvent.BUTTON3)
                       popUpMenu.show(ep,ev.getX(),ev.getY());   
                }
            });

        }catch( Exception ex){
           // some.console.writeUnexpectedError( ex.toString() );
        }
        
        TextLineNumber tln = new TextLineNumber(ep);

        ImageIcon icon;
        JLabel label;
        icon = createImageIcon("/images/unsaved.png");
        label = new JLabel("New");
        ep.setFont(new Font("Courier New", 0, 14));
        boolean flagOld = false;
        String urlFile = null;
        if (file != null) {
            if (!"".equals(file.getName())) {
                icon = createImageIcon("/images/saved.png");
                label = new JLabel(file.getName());
                urlFile = file.getPath();
                ep.setText(fileManagement.readFile(file));
            }
            flagOld = true;
        }

        ep.setEditable(true);
        
        DocumentListener dl;
        dl = new DocumentListener() {

            public void insertUpdate(DocumentEvent e) {
                JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
                
                String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
                ImageIcon iconjl = null;
                try {
                    iconjl = iconjl = createImageIcon("/images/unsaved.png");
                } catch (IOException ex) {
                    Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                JPanel pnl = new JPanel();
                pnl.setOpaque(false);
                pnlTab.remove(0);
                pnl.add(new JLabel(iconjl));
                pnl.add(pnlTab.getComponent(0));
                pnl.add(pnlTab.getComponent(0));
                some.tabs.setTabComponentAt(some.tabs.getSelectedIndex(), pnl);
                ((Triplet) hmAreas.get(Integer.parseInt(s1))).set1(false);
            }

            public void removeUpdate(DocumentEvent e) {
                JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getSelectedIndex());
                
                String s1 = ((JButton) pnlTab.getComponent(2)).getActionCommand();
                ImageIcon iconjl = null;
                try {
                    iconjl = iconjl = createImageIcon("/images/unsaved.png");
                } catch (IOException ex) {
                    Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                JPanel pnl = new JPanel();
                pnl.setOpaque(false);
                pnlTab.remove(0);
                pnl.add(new JLabel(iconjl));
                pnl.add(pnlTab.getComponent(0));
                pnl.add(pnlTab.getComponent(0));
                some.tabs.setTabComponentAt(some.tabs.getSelectedIndex(), pnl);
                ((Triplet) hmAreas.get(Integer.parseInt(s1))).set1(false);
            }

            public void changedUpdate(DocumentEvent e) {
                //Plain text components don't fire these events.
            }
        };
        ep.getDocument().addDocumentListener(dl);
        
        
        
         
         
         
        JScrollPane jScroll = new JScrollPane(ep);
        jScroll.setRowHeaderView(tln);
        tp.addTab(null, jScroll);

        JButton tabCloseButton = ButtonPro("/images/closeTabButton.png", "Close");
        tabCloseButton.setActionCommand("" + (tabCounter));

        hmAreas.put(tabCounter, new Triplet(flagOld, urlFile, ep));

        ActionListener al;
        al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JButton btn = (JButton) ae.getSource();
                String s1 = btn.getActionCommand();
                for (int i = 0; i < tp.getTabCount(); i++) {
                    JPanel pnl = (JPanel) tp.getTabComponentAt(i);
                    btn = (JButton) pnl.getComponent(2);
                    String s2 = btn.getActionCommand();
                    if (s1.equals(s2)) {
                        tp.removeTabAt(i);
                        break;
                    }
                }
            }
        };
        tabCloseButton.addActionListener(al);

        if (tabCounter != 0) {
            JPanel pnl = new JPanel();
            pnl.setOpaque(false);

            JLabel picLabel = new JLabel(icon);
            pnl.add(picLabel);
            pnl.add(label);
            pnl.add(tabCloseButton);

            tp.setTabComponentAt(tp.getTabCount() - 1, pnl);
            tp.setSelectedIndex(tp.getTabCount() - 1);
        }

        tabCounter++;
        
        if (flagOld) {
            final JPanel pnlTab = (JPanel) some.tabs.getTabComponentAt(some.tabs.getTabCount() - 2);
            some.tabs.remove(some.tabs.getTabCount() - 2);
            JTextPane jEditor = new JTextPane();
            jEditor.setEditable(false);
            some.tabs.addTab(null, new JScrollPane(jEditor));
            tabCounter++;
            some.tabs.setTabComponentAt(some.tabs.getTabCount() - 1, pnlTab);
        }
    }

    /**
     * Function that personalize a existing button in the gui.
     *
     * @param button
     * @param label
     * @param pathIcon
     * @param toolTip
     */
    static void EditButtonPro(final JButton b, String label, String pathIcon, String toolTip) {
        ImageIcon icon = null;
        try {
            icon = createImageIcon(pathIcon);
        } catch (IOException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        b.setIcon(icon);
        b.setText(label);
        final Border raisedBevelBorder = BorderFactory.createRaisedBevelBorder();
        final Insets insets = raisedBevelBorder.getBorderInsets(b);
        final EmptyBorder emptyBorder = new EmptyBorder(insets);
        b.setBorder(emptyBorder);
        b.setFocusPainted(false);
        b.setOpaque(false);
        b.setContentAreaFilled(false);
        b.setToolTipText(toolTip);
        b.getModel().addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                ButtonModel model = (ButtonModel) e.getSource();
                if (model.isRollover()) {
                    b.setBorder(raisedBevelBorder);
                } else {
                    b.setBorder(emptyBorder);
                }
            }
        });
    }

    /**
     * Function that personalize all buttons in the Gui
     *
     * @throws IOException
     */
    static void makeButtons() throws IOException {
        JButton btn;
        JLabel lbl = new JLabel("   ");

        EditButtonPro(some.btnNew, null, "/images/New_32x32.png", "New file");
        EditButtonPro(some.btnOpen, null, "/images/Folder_32x32.png", "Open file");
        EditButtonPro(some.btnSave, null, "/images/Save_32x32.png", "Save file");
        EditButtonPro(some.btnSaveAll, null, "/images/SaveAll_32x32.png", "Save all files");

        EditButtonPro(some.btnCompile, null, "/images/Application_32x32.png", "Compile");
        EditButtonPro(some.btnConvert, null, "/images/Properties_32x32.png", "Convert");
        EditButtonPro(some.btnAvroraz, null, "/images/Avroraz_32x32.png", "Send to AvroraZ");
        EditButtonPro(some.btnMakeAll, null, "/images/Play_32x32.png", "Compile, convert and sent it to AvroraZ");

        some.jToolBar1.add(Box.createHorizontalGlue());
        btn = ButtonPro("/images/Settings_32x32.png", "Options");
        some.jToolBar1.add(btn);
        some.jToolBar1.addSeparator();
        btn = ButtonPro("/images/Help_32x32.png", "Help");
        some.jToolBar1.add(btn);
    }

    private static void btnAvroraz() {

        //window.setModalExclusionType(Dialog.ModalExclusionType.NO_EXCLUDE);
        commandsAvroraZ jd = new commandsAvroraZ(some, true);
        //Opening in the center of the screen
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - jd.getWidth()) / 2;
        int y = (screenSize.height - jd.getHeight()) / 2;
        jd.setLocation(x, y);
        jd.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
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
                java.util.logging.Logger.getLogger(TabPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(TabPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(TabPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(TabPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            /*java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
             new TabPanel().setVisible(true);
             }
             });*/
            doc = new DocumentNC();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        makeButtons();
                    } catch (IOException ex) {
                        Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ;
                    final JTextPane ep = new JTextPane();
                    ep.setEditable(false);
                    some.jtpConsole.setEditable(false);
                    some.tabs.addTab(null, new JScrollPane(ep));
                    // some.jTabbedPane1.addTab (null, null);
                    FlowLayout f = new FlowLayout(FlowLayout.CENTER, 5, 0);

                    // Make a small JPanel with the layout and make it non-opaque
                    final JPanel pnlTab = new JPanel(f);
                    pnlTab.setOpaque(false);
                    // Create a JButton for adding the tabs
                    JButton addTab = new JButton("+");
                    addTab.setOpaque(false); //
                    addTab.setBorder(null);
                    addTab.setContentAreaFilled(false);
                    addTab.setFocusPainted(false);
                    addTab.setFocusable(false);
                    pnlTab.add(addTab);

                    some.tabs.setTabComponentAt(some.tabs.getTabCount() - 1, pnlTab);

                    ActionListener listener = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                some.tabs.remove(some.tabs.getTabCount() - 1);
                                newTab(some.tabs, null);
                                some.tabs.addTab(null, new JScrollPane(ep));
                                tabCounter++;
                                some.tabs.setTabComponentAt(some.tabs.getTabCount() - 1, pnlTab);

                            } catch (IOException ex) {
                                Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (Exception ex) {
                                Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    };
                    addTab.setFocusable(false);
                    addTab.addActionListener(listener);
                    some.tabs.setVisible(true);
                    some.setTitle("IDE-NesC 1.0");

                    some.setMinimumSize(new Dimension(600, 400));
                    some.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
                    some.setVisible(true);

                    UIManager.LookAndFeelInfo piel[] = UIManager.getInstalledLookAndFeels();
                    for (UIManager.LookAndFeelInfo piel1 : piel) {
                        System.out.println("Nombre Skin = " + piel1.getClassName());
                        //some.setVisible(true);
                    }
                }
            ;/*
             addTab.setFocusable(false);
             addTab.addActionListener(listener);
             some.tabs.setVisible(true);
             some.setTitle("IDE-NesC 1.0");
             some.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
             some.setVisible(true);*/

        } );
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(TabPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem New;
    private javax.swing.JButton btCheck;
    private javax.swing.JButton btnAvroraz;
    private javax.swing.JButton btnCompile;
    private javax.swing.JButton btnConvert;
    private javax.swing.JButton btnMakeAll;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnSaveAll;
    private javax.swing.JMenuItem close;
    private javax.swing.JMenu file;
    private javax.swing.JToolBar info;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextPane jtpConsole;
    private java.awt.Label label1;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem open;
    private javax.swing.JMenu run;
    private javax.swing.JMenuItem save;
    private javax.swing.JMenuItem saveAll;
    private javax.swing.JMenuItem saveAs;
    private javax.swing.JTabbedPane tabs;
    // End of variables declaration//GEN-END:variables
}
