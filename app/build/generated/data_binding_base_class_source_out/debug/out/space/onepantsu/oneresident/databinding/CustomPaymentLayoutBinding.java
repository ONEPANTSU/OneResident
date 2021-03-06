// Generated by view binder compiler. Do not edit!
package space.onepantsu.oneresident.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import space.onepantsu.oneresident.R;

public final class CustomPaymentLayoutBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button changeDateButton;

  @NonNull
  public final Button paymentInfo;

  @NonNull
  public final Button toPayButton;

  private CustomPaymentLayoutBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button changeDateButton, @NonNull Button paymentInfo, @NonNull Button toPayButton) {
    this.rootView = rootView;
    this.changeDateButton = changeDateButton;
    this.paymentInfo = paymentInfo;
    this.toPayButton = toPayButton;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static CustomPaymentLayoutBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static CustomPaymentLayoutBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.custom_payment_layout, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static CustomPaymentLayoutBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.changeDateButton;
      Button changeDateButton = ViewBindings.findChildViewById(rootView, id);
      if (changeDateButton == null) {
        break missingId;
      }

      id = R.id.paymentInfo;
      Button paymentInfo = ViewBindings.findChildViewById(rootView, id);
      if (paymentInfo == null) {
        break missingId;
      }

      id = R.id.toPayButton;
      Button toPayButton = ViewBindings.findChildViewById(rootView, id);
      if (toPayButton == null) {
        break missingId;
      }

      return new CustomPaymentLayoutBinding((ConstraintLayout) rootView, changeDateButton,
          paymentInfo, toPayButton);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
