/***********************************************************************
 *
 * $CVSHeader$
 *
 * This file is part of WebScarab, an Open Web Application Security
 * Project utility. For details, please see http://www.owasp.org/
 *
 * Copyright (c) 2002 - 2004 Rogan Dawes
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * Getting Source
 * ==============
 *
 * Source for this application is maintained at Sourceforge.net, a
 * repository for free software projects.
 * 
 * For details, please see http://www.sourceforge.net/projects/owasp
 *
 */

/*
 * TranscoderFrame.java
 *
 * Created on 29 May 2003, 03:18
 */

package org.owasp.webscarab.ui.swing;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.Transferable;

import java.awt.event.ActionEvent;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.swing.Action;
import javax.swing.AbstractAction;

import javax.swing.text.Document;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

import javax.swing.event.UndoableEditListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.undo.UndoManager;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;

import java.util.Hashtable;

import org.owasp.webscarab.util.Encoding;

/**
 *
 * @author  rdawes
 */

public class TranscoderFrame extends javax.swing.JFrame implements ClipboardOwner {
    
    private Clipboard clipboard = getToolkit().getSystemClipboard();
    protected UndoManager undo = new UndoManager();
    protected UndoAction undoAction;
    protected RedoAction redoAction;
    private Hashtable actions;
    
    private static sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
    private static sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    
    /** Creates new form TranscoderFrame */
    public TranscoderFrame() {
        initComponents();
        createActionTable(textPane);

        undoAction = new UndoAction();
        editMenu.add(undoAction);
        redoAction = new RedoAction();
        editMenu.add(redoAction);
        
        editMenu.addSeparator();
 
        //These actions come from the default editor kit.
        //Get the ones we want and stick them in the menu.
        editMenu.add(getActionByName(DefaultEditorKit.cutAction));
        editMenu.add(getActionByName(DefaultEditorKit.copyAction));
        editMenu.add(getActionByName(DefaultEditorKit.pasteAction));
 
        editMenu.addSeparator();
 
        editMenu.add(getActionByName(DefaultEditorKit.selectAllAction));
         
        Document d = textPane.getDocument();
        d.addUndoableEditListener(new MyUndoableEditListener());
    }
    
    //The following two methods allow us to find an
    //action provided by the editor kit by its name.
    private void createActionTable(JTextComponent textComponent) {
        actions = new Hashtable();
        Action[] actionsArray = textComponent.getActions();
        for (int i = 0; i < actionsArray.length; i++) {
            Action a = actionsArray[i];
            actions.put(a.getValue(Action.NAME), a);
        }
    }
                                                                                
    private Action getActionByName(String name) {
        return (Action)(actions.get(name));
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
        java.awt.GridBagConstraints gridBagConstraints;

        jScrollPane1 = new javax.swing.JScrollPane();
        textPane = new javax.swing.JTextPane();
        jPanel1 = new javax.swing.JPanel();
        urlEncodeButton = new javax.swing.JButton();
        urlDecodeButton = new javax.swing.JButton();
        base64EncodeButton = new javax.swing.JButton();
        base64DecodeButton = new javax.swing.JButton();
        md5hashButton = new javax.swing.JButton();
        sha1hashButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        editMenu = new javax.swing.JMenu();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        setTitle("Transcoder");
        jScrollPane1.setViewportView(textPane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(jScrollPane1, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        urlEncodeButton.setText("URL encode");
        urlEncodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlEncodeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(urlEncodeButton, gridBagConstraints);

        urlDecodeButton.setText("URL decode");
        urlDecodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                urlDecodeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(urlDecodeButton, gridBagConstraints);

        base64EncodeButton.setText("Base64 encode");
        base64EncodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                base64EncodeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(base64EncodeButton, gridBagConstraints);

        base64DecodeButton.setText("Base64 decode");
        base64DecodeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                base64DecodeButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(base64DecodeButton, gridBagConstraints);

        md5hashButton.setText("MD5 hash");
        md5hashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                md5hashButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(md5hashButton, gridBagConstraints);

        sha1hashButton.setText("SHA-1 hash");
        sha1hashButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sha1hashButtonActionPerformed(evt);
            }
        });

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 1.0;
        jPanel1.add(sha1hashButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(jPanel1, gridBagConstraints);

        editMenu.setText("Edit");
        jMenuBar1.add(editMenu);

        setJMenuBar(jMenuBar1);

        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setBounds((screenSize.width-600)/2, (screenSize.height-400)/2, 600, 400);
    }//GEN-END:initComponents
    
    private void sha1hashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sha1hashButtonActionPerformed
        if (textPane.getSelectionEnd() == textPane.getSelectionStart())
            textPane.select(0,textPane.getText().length());
        textPane.replaceSelection(Encoding.hashSHA(textPane.getSelectedText()));
    }//GEN-LAST:event_sha1hashButtonActionPerformed
    
    private void md5hashButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_md5hashButtonActionPerformed
        if (textPane.getSelectionEnd() == textPane.getSelectionStart())
            textPane.select(0,textPane.getText().length());
        textPane.replaceSelection(Encoding.hashMD5(textPane.getSelectedText()));
    }//GEN-LAST:event_md5hashButtonActionPerformed
    
    private void base64DecodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_base64DecodeButtonActionPerformed
        try { 
            if (textPane.getSelectionEnd() == textPane.getSelectionStart())
                textPane.select(0,textPane.getText().length());
            textPane.replaceSelection(new String(Encoding.base64decode(textPane.getSelectedText())));
        } catch (Exception e) {
            textPane.setText("Exception! " + e.toString());
        }
    }//GEN-LAST:event_base64DecodeButtonActionPerformed
    
    private void urlDecodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlDecodeButtonActionPerformed
        if (textPane.getSelectionEnd() == textPane.getSelectionStart())
            textPane.select(0,textPane.getText().length());
        textPane.replaceSelection(urlDecode(textPane.getSelectedText()));
    }//GEN-LAST:event_urlDecodeButtonActionPerformed
            
    private void urlEncodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_urlEncodeButtonActionPerformed
        if (textPane.getSelectionEnd() == textPane.getSelectionStart())
            textPane.select(0,textPane.getText().length());
        textPane.replaceSelection(urlEncode(textPane.getSelectedText()));
    }//GEN-LAST:event_urlEncodeButtonActionPerformed
    
    private void base64EncodeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_base64EncodeButtonActionPerformed
        if (textPane.getSelectionEnd() == textPane.getSelectionStart())
            textPane.select(0,textPane.getText().length());
        textPane.replaceSelection(Encoding.base64encode(textPane.getSelectedText().getBytes()));
    }//GEN-LAST:event_base64EncodeButtonActionPerformed
    
    /** Notifies this object that it is no longer the owner of
     * the contents of the clipboard.
     * @param clipboard the clipboard that is no longer owned
     * @param contents the contents which this owner had placed on the clipboard
     *
     */
    public void lostOwnership(Clipboard clipboard, Transferable contents) {
    }
    
    
    
    /**
     *  Description of the Method
     *
     *@param  str  Description of the Parameter
     *@return      Description of the Return Value
     */
    /* 
    public static String unicodeDecode( String str ) {
        // FIXME: TOTALLY EXPERIMENTAL
        try {
            ByteBuffer bbuf = ByteBuffer.allocate( str.length() );
            bbuf.put( str.getBytes() );
            Charset charset = Charset.forName( "ISO-8859-1" );
            CharsetDecoder decoder = charset.newDecoder();
            CharBuffer cbuf = decoder.decode( bbuf );
            return ( cbuf.toString() );
        }
        catch ( Exception e ) {
            return ( "Encoding problem" );
        }
    }
     */
    
    
    /**
     *  Description of the Method
     *
     *@param  str  Description of the Parameter
     *@return      Description of the Return Value
     */
    /*
    public static String unicodeEncode( String str ) {
        // FIXME: TOTALLY EXPERIMENTAL
        try {
            Charset charset = Charset.forName( "ISO-8859-1" );
            CharsetEncoder encoder = charset.newEncoder();
            ByteBuffer bbuf = encoder.encode( CharBuffer.wrap( str ) );
            return ( new String( bbuf.array() ) );
        }
        catch ( Exception e ) {
            return ( "Encoding problem" );
        }
    }
     */
    
    
    /**
     *  Description of the Method
     *
     *@param  str  Description of the Parameter
     *@return      Description of the Return Value
     */
    public static String urlDecode( String str ) {
        try {
            return ( URLDecoder.decode( str, "utf-8" ) );
        }
        catch ( Exception e ) {
            return ( "Decoding error" );
        }
    }
    
    
    /**
     *  Description of the Method
     *
     *@param  str  Description of the Parameter
     *@return      Description of the Return Value
     */
    public static String urlEncode( String str ) {
        try {
            return ( URLEncoder.encode( str, "utf-8" ) );
        }
        catch ( Exception e ) {
            return ( "Encoding error" );
        }
    }
    
    
    /** Exit the Application */
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        TranscoderFrame tf = new TranscoderFrame();
        tf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });
        tf.show();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton base64DecodeButton;
    private javax.swing.JButton base64EncodeButton;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton md5hashButton;
    private javax.swing.JButton sha1hashButton;
    private javax.swing.JTextPane textPane;
    private javax.swing.JButton urlDecodeButton;
    private javax.swing.JButton urlEncodeButton;
    // End of variables declaration//GEN-END:variables
    
    class UndoAction extends AbstractAction {
        public UndoAction() {
            super("Undo");
            setEnabled(false);
        }
        
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
                System.out.println("Unable to undo: " + ex);
                ex.printStackTrace();
            }
            updateUndoState();
            redoAction.updateRedoState();
        }
        
        protected void updateUndoState() {
            if (undo.canUndo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getUndoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }
    
    
    class RedoAction extends AbstractAction {
        public RedoAction() {
            super("Redo");
            setEnabled(false);
        }
        
        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                System.out.println("Unable to redo: " + ex);
                ex.printStackTrace();
            }
            updateRedoState();
            undoAction.updateUndoState();
        }
        
        protected void updateRedoState() {
            if (undo.canRedo()) {
                setEnabled(true);
                putValue(Action.NAME, undo.getRedoPresentationName());
            } else {
                setEnabled(false);
                putValue(Action.NAME, "Redo");
            }
        }
    }
    
    
    //This one listens for edits that can be undone.
    protected class MyUndoableEditListener
    implements UndoableEditListener {
        public void undoableEditHappened(UndoableEditEvent e) {
            //Remember the edit and update the menus.
            undo.addEdit(e.getEdit());
            undoAction.updateUndoState();
            redoAction.updateRedoState();
        }
    }
    
    
}
