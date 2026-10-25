/*
 * SPDX-FileCopyrightText: 2026 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package miui.app;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ListAdapter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** MIUI alert dialog on top of the AOSP one, adding the optional check box. */
public class AlertDialog extends android.app.AlertDialog {
    private static final String TAG = "MiuiCompatAlertDialog";

    private CheckBox mCheckBox;

    protected AlertDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public boolean isChecked() {
        return mCheckBox != null && mCheckBox.isChecked();
    }

    public static class Builder extends android.app.AlertDialog.Builder {
        private boolean mCheckBoxChecked;
        private CharSequence mCheckBoxMessage;
        private boolean mHasView;

        public Builder(Context context) {
            super(context);
        }

        public Builder(Context context, int themeResId) {
            super(context, themeResId);
        }

        public Builder setCheckBox(boolean checked, CharSequence message) {
            mCheckBoxChecked = checked;
            mCheckBoxMessage = message;
            return this;
        }

        @Override
        public AlertDialog create() {
            AlertDialog dialog = new AlertDialog(getContext(), 0);
            try {
                // Same as android.app.AlertDialog.Builder#create(), on our subclass
                Field paramsField = android.app.AlertDialog.Builder.class.getDeclaredField("P");
                paramsField.setAccessible(true);
                Object params = paramsField.get(this);
                Field alertField = android.app.AlertDialog.class.getDeclaredField("mAlert");
                alertField.setAccessible(true);
                Object alert = alertField.get(dialog);
                Method apply = params.getClass().getMethod("apply", alert.getClass());
                apply.invoke(params, alert);

                boolean cancelable = getBoolean(params, "mCancelable");
                dialog.setCancelable(cancelable);
                if (cancelable) {
                    dialog.setCanceledOnTouchOutside(true);
                }
                dialog.setOnCancelListener(
                        (DialogInterface.OnCancelListener) get(params, "mOnCancelListener"));
                dialog.setOnDismissListener(
                        (DialogInterface.OnDismissListener) get(params, "mOnDismissListener"));
                DialogInterface.OnKeyListener keyListener =
                        (DialogInterface.OnKeyListener) get(params, "mOnKeyListener");
                if (keyListener != null) {
                    dialog.setOnKeyListener(keyListener);
                }
            } catch (ReflectiveOperationException e) {
                Log.e(TAG, "Failed to apply alert params", e);
            }

            if (mCheckBoxMessage != null && !mHasView) {
                Context context = dialog.getContext();
                CheckBox checkBox = new CheckBox(context);
                checkBox.setChecked(mCheckBoxChecked);
                checkBox.setText(mCheckBoxMessage);
                FrameLayout frame = new FrameLayout(context);
                int padding = (int) (20 * context.getResources().getDisplayMetrics().density);
                frame.setPadding(padding, 0, padding, 0);
                frame.addView(checkBox);
                dialog.setView(frame);
                dialog.mCheckBox = checkBox;
            }
            return dialog;
        }

        @Override
        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

        private static Object get(Object obj, String name) throws ReflectiveOperationException {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(obj);
        }

        private static boolean getBoolean(Object obj, String name)
                throws ReflectiveOperationException {
            Field field = obj.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.getBoolean(obj);
        }

        @Override
        public Builder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
            super.setAdapter(adapter, listener);
            return this;
        }

        @Override
        public Builder setCancelable(boolean cancelable) {
            super.setCancelable(cancelable);
            return this;
        }

        @Override
        public Builder setCursor(Cursor cursor, DialogInterface.OnClickListener listener,
                String labelColumn) {
            super.setCursor(cursor, listener, labelColumn);
            return this;
        }

        @Override
        public Builder setCustomTitle(View customTitleView) {
            super.setCustomTitle(customTitleView);
            return this;
        }

        @Override
        public Builder setIcon(int iconId) {
            super.setIcon(iconId);
            return this;
        }

        @Override
        public Builder setIcon(Drawable icon) {
            super.setIcon(icon);
            return this;
        }

        @Override
        public Builder setIconAttribute(int attrId) {
            super.setIconAttribute(attrId);
            return this;
        }

        @Override
        public Builder setItems(int itemsId, DialogInterface.OnClickListener listener) {
            super.setItems(itemsId, listener);
            return this;
        }

        @Override
        public Builder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
            super.setItems(items, listener);
            return this;
        }

        @Override
        public Builder setMessage(int messageId) {
            super.setMessage(messageId);
            return this;
        }

        @Override
        public Builder setMessage(CharSequence message) {
            super.setMessage(message);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(int itemsId, boolean[] checkedItems,
                DialogInterface.OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(itemsId, checkedItems, listener);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(Cursor cursor, String isCheckedColumn,
                String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
            return this;
        }

        @Override
        public Builder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems,
                DialogInterface.OnMultiChoiceClickListener listener) {
            super.setMultiChoiceItems(items, checkedItems, listener);
            return this;
        }

        @Override
        public Builder setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
            super.setNegativeButton(textId, listener);
            return this;
        }

        @Override
        public Builder setNegativeButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            super.setNegativeButton(text, listener);
            return this;
        }

        @Override
        public Builder setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
            super.setNeutralButton(textId, listener);
            return this;
        }

        @Override
        public Builder setNeutralButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            super.setNeutralButton(text, listener);
            return this;
        }

        @Override
        public Builder setOnCancelListener(DialogInterface.OnCancelListener listener) {
            super.setOnCancelListener(listener);
            return this;
        }

        @Override
        public Builder setOnDismissListener(DialogInterface.OnDismissListener listener) {
            super.setOnDismissListener(listener);
            return this;
        }

        @Override
        public Builder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
            super.setOnItemSelectedListener(listener);
            return this;
        }

        @Override
        public Builder setOnKeyListener(DialogInterface.OnKeyListener listener) {
            super.setOnKeyListener(listener);
            return this;
        }

        @Override
        public Builder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
            super.setPositiveButton(textId, listener);
            return this;
        }

        @Override
        public Builder setPositiveButton(CharSequence text,
                DialogInterface.OnClickListener listener) {
            super.setPositiveButton(text, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(int itemsId, int checkedItem,
                DialogInterface.OnClickListener listener) {
            super.setSingleChoiceItems(itemsId, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn,
                DialogInterface.OnClickListener listener) {
            super.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(ListAdapter adapter, int checkedItem,
                DialogInterface.OnClickListener listener) {
            super.setSingleChoiceItems(adapter, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setSingleChoiceItems(CharSequence[] items, int checkedItem,
                DialogInterface.OnClickListener listener) {
            super.setSingleChoiceItems(items, checkedItem, listener);
            return this;
        }

        @Override
        public Builder setTitle(int titleId) {
            super.setTitle(titleId);
            return this;
        }

        @Override
        public Builder setTitle(CharSequence title) {
            super.setTitle(title);
            return this;
        }

        @Override
        public Builder setView(int layoutResId) {
            super.setView(layoutResId);
            mHasView = true;
            return this;
        }

        @Override
        public Builder setView(View view) {
            super.setView(view);
            mHasView = true;
            return this;
        }
    }
}
