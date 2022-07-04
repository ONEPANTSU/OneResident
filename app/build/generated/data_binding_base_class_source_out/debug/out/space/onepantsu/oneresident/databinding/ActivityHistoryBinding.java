// Generated by view binder compiler. Do not edit!
package space.onepantsu.oneresident.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import space.onepantsu.oneresident.R;

public final class ActivityHistoryBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backButtonFromHistoryActivity;

  @NonNull
  public final ImageButton deleteAllFromHistoryActivity;

  @NonNull
  public final LinearLayout historyLinear;

  private ActivityHistoryBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backButtonFromHistoryActivity,
      @NonNull ImageButton deleteAllFromHistoryActivity, @NonNull LinearLayout historyLinear) {
    this.rootView = rootView;
    this.backButtonFromHistoryActivity = backButtonFromHistoryActivity;
    this.deleteAllFromHistoryActivity = deleteAllFromHistoryActivity;
    this.historyLinear = historyLinear;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityHistoryBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityHistoryBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_history, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityHistoryBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButtonFromHistoryActivity;
      ImageButton backButtonFromHistoryActivity = ViewBindings.findChildViewById(rootView, id);
      if (backButtonFromHistoryActivity == null) {
        break missingId;
      }

      id = R.id.deleteAllFromHistoryActivity;
      ImageButton deleteAllFromHistoryActivity = ViewBindings.findChildViewById(rootView, id);
      if (deleteAllFromHistoryActivity == null) {
        break missingId;
      }

      id = R.id.historyLinear;
      LinearLayout historyLinear = ViewBindings.findChildViewById(rootView, id);
      if (historyLinear == null) {
        break missingId;
      }

      return new ActivityHistoryBinding((ConstraintLayout) rootView, backButtonFromHistoryActivity,
          deleteAllFromHistoryActivity, historyLinear);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
