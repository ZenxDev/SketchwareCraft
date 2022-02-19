package mod.hosni.fraj.compilerlog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import com.besome.sketch.tools.CompileLogActivity;

import mod.agus.jcoderz.lib.FilePathUtil;
import mod.agus.jcoderz.lib.FileUtil;

public class CompileErrorSaver {

    private static final String MESSAGE_NO_COMPILE_ERRORS_SAVED =
            "No compile errors on the last build. ( try to run the app )";

    public String sc_id;
    public FilePathUtil filePathUtil = new FilePathUtil();
    public String path;

    /**
     * Create this helper class for saving compile errors.
     *
     * @param sc_id The Sketchware project ID for the project to operate on, like 605
     */
    public CompileErrorSaver(String sc_id) {
        this.sc_id = sc_id;
        path = FilePathUtil.getLastCompileLogPath(sc_id);
        check();
    }

    /**
     * Save a compile error in the project's last compile error file.
     *
     * @param errorText The text to save, if possible, with detailed messages
     */
    public void setErrorText(String errorText) {
        FileUtil.deleteFile(path);
        FileUtil.writeFile(path, errorText);
    }

    /**
     * Show an {@link AlertDialog} that displays the last saved error to the user.
     *
     * @param context The context to show the dialog on
     */
    public void showLastErrors(Context context) {
        Intent intent = new Intent(context, CompileLogActivity.class);
        intent.putExtra("error", getLog());
        intent.putExtra("showingLastError", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    /** Clear the last saved error text. */
    public void clear() {
        FileUtil.deleteFile(path);
    }

    /** @return The last saved error text */
    public String getLog() {
        return FileUtil.readFile(path);
    }

    /**
     * Check if the last saved error text file exists, if not, it'll get created and {@link
     * CompileErrorSaver#MESSAGE_NO_COMPILE_ERRORS_SAVED} will be written to it.
     */
    public void check() {
        if (!FileUtil.isExistFile(path)) {
            FileUtil.writeFile(path, MESSAGE_NO_COMPILE_ERRORS_SAVED);
        }
    }
}
