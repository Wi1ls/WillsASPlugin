package wills;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import org.apache.http.util.TextUtils;
import wills.widgets.SearchDialogModelFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
//主动打开搜索框
public class FindWithDialogAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        String clipText = getClipBoardText();
        new SearchDialogModelFrame(e.getProject(),clipText);
    }

    private String getClipBoardText() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(null);
        if (content.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            //文本类型
            try {
                String text = (String) content.getTransferData(DataFlavor.stringFlavor);
                if (TextUtils.isEmpty(text)) {
                    return "";
                }
                return text;
            } catch (Exception e) {
                return "";
            }
        }
        return "";
    }
}
