// Generated by view binder compiler. Do not edit!
package space.onepantsu.oneresident.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import space.onepantsu.oneresident.R;

public final class ActivityResidentInfoBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageButton backButtonFromResidentActivity;

  @NonNull
  public final ImageButton buttonAdd;

  @NonNull
  public final TextView textCity;

  @NonNull
  public final TextView textComment;

  @NonNull
  public final TextView textDate;

  @NonNull
  public final TextView textFlat;

  @NonNull
  public final TextView textHouse;

  @NonNull
  public final TextView textLevel;

  @NonNull
  public final TextView textName;

  @NonNull
  public final TextView textObject;

  @NonNull
  public final TextView textPeriod;

  @NonNull
  public final TextView textPhone;

  @NonNull
  public final TextView textPrice;

  @NonNull
  public final TextView textSecondName;

  @NonNull
  public final TextView textStreet;

  @NonNull
  public final TextView textSurname;

  @NonNull
  public final TextView textViewAddressHeader;

  @NonNull
  public final TextView textViewCity;

  @NonNull
  public final TextView textViewComment;

  @NonNull
  public final TextView textViewDate;

  @NonNull
  public final TextView textViewFlat;

  @NonNull
  public final TextView textViewHouse;

  @NonNull
  public final TextView textViewLevel;

  @NonNull
  public final TextView textViewName;

  @NonNull
  public final TextView textViewObject;

  @NonNull
  public final TextView textViewPeriod;

  @NonNull
  public final TextView textViewPhone;

  @NonNull
  public final TextView textViewPrice;

  @NonNull
  public final TextView textViewRentHeader;

  @NonNull
  public final TextView textViewResidentHeader;

  @NonNull
  public final TextView textViewSecondName;

  @NonNull
  public final TextView textViewStreet;

  @NonNull
  public final TextView textViewSurname;

  private ActivityResidentInfoBinding(@NonNull ConstraintLayout rootView,
      @NonNull ImageButton backButtonFromResidentActivity, @NonNull ImageButton buttonAdd,
      @NonNull TextView textCity, @NonNull TextView textComment, @NonNull TextView textDate,
      @NonNull TextView textFlat, @NonNull TextView textHouse, @NonNull TextView textLevel,
      @NonNull TextView textName, @NonNull TextView textObject, @NonNull TextView textPeriod,
      @NonNull TextView textPhone, @NonNull TextView textPrice, @NonNull TextView textSecondName,
      @NonNull TextView textStreet, @NonNull TextView textSurname,
      @NonNull TextView textViewAddressHeader, @NonNull TextView textViewCity,
      @NonNull TextView textViewComment, @NonNull TextView textViewDate,
      @NonNull TextView textViewFlat, @NonNull TextView textViewHouse,
      @NonNull TextView textViewLevel, @NonNull TextView textViewName,
      @NonNull TextView textViewObject, @NonNull TextView textViewPeriod,
      @NonNull TextView textViewPhone, @NonNull TextView textViewPrice,
      @NonNull TextView textViewRentHeader, @NonNull TextView textViewResidentHeader,
      @NonNull TextView textViewSecondName, @NonNull TextView textViewStreet,
      @NonNull TextView textViewSurname) {
    this.rootView = rootView;
    this.backButtonFromResidentActivity = backButtonFromResidentActivity;
    this.buttonAdd = buttonAdd;
    this.textCity = textCity;
    this.textComment = textComment;
    this.textDate = textDate;
    this.textFlat = textFlat;
    this.textHouse = textHouse;
    this.textLevel = textLevel;
    this.textName = textName;
    this.textObject = textObject;
    this.textPeriod = textPeriod;
    this.textPhone = textPhone;
    this.textPrice = textPrice;
    this.textSecondName = textSecondName;
    this.textStreet = textStreet;
    this.textSurname = textSurname;
    this.textViewAddressHeader = textViewAddressHeader;
    this.textViewCity = textViewCity;
    this.textViewComment = textViewComment;
    this.textViewDate = textViewDate;
    this.textViewFlat = textViewFlat;
    this.textViewHouse = textViewHouse;
    this.textViewLevel = textViewLevel;
    this.textViewName = textViewName;
    this.textViewObject = textViewObject;
    this.textViewPeriod = textViewPeriod;
    this.textViewPhone = textViewPhone;
    this.textViewPrice = textViewPrice;
    this.textViewRentHeader = textViewRentHeader;
    this.textViewResidentHeader = textViewResidentHeader;
    this.textViewSecondName = textViewSecondName;
    this.textViewStreet = textViewStreet;
    this.textViewSurname = textViewSurname;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityResidentInfoBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityResidentInfoBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_resident_info, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityResidentInfoBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.backButtonFromResidentActivity;
      ImageButton backButtonFromResidentActivity = ViewBindings.findChildViewById(rootView, id);
      if (backButtonFromResidentActivity == null) {
        break missingId;
      }

      id = R.id.buttonAdd;
      ImageButton buttonAdd = ViewBindings.findChildViewById(rootView, id);
      if (buttonAdd == null) {
        break missingId;
      }

      id = R.id.textCity;
      TextView textCity = ViewBindings.findChildViewById(rootView, id);
      if (textCity == null) {
        break missingId;
      }

      id = R.id.textComment;
      TextView textComment = ViewBindings.findChildViewById(rootView, id);
      if (textComment == null) {
        break missingId;
      }

      id = R.id.textDate;
      TextView textDate = ViewBindings.findChildViewById(rootView, id);
      if (textDate == null) {
        break missingId;
      }

      id = R.id.textFlat;
      TextView textFlat = ViewBindings.findChildViewById(rootView, id);
      if (textFlat == null) {
        break missingId;
      }

      id = R.id.textHouse;
      TextView textHouse = ViewBindings.findChildViewById(rootView, id);
      if (textHouse == null) {
        break missingId;
      }

      id = R.id.textLevel;
      TextView textLevel = ViewBindings.findChildViewById(rootView, id);
      if (textLevel == null) {
        break missingId;
      }

      id = R.id.textName;
      TextView textName = ViewBindings.findChildViewById(rootView, id);
      if (textName == null) {
        break missingId;
      }

      id = R.id.textObject;
      TextView textObject = ViewBindings.findChildViewById(rootView, id);
      if (textObject == null) {
        break missingId;
      }

      id = R.id.textPeriod;
      TextView textPeriod = ViewBindings.findChildViewById(rootView, id);
      if (textPeriod == null) {
        break missingId;
      }

      id = R.id.textPhone;
      TextView textPhone = ViewBindings.findChildViewById(rootView, id);
      if (textPhone == null) {
        break missingId;
      }

      id = R.id.textPrice;
      TextView textPrice = ViewBindings.findChildViewById(rootView, id);
      if (textPrice == null) {
        break missingId;
      }

      id = R.id.textSecondName;
      TextView textSecondName = ViewBindings.findChildViewById(rootView, id);
      if (textSecondName == null) {
        break missingId;
      }

      id = R.id.textStreet;
      TextView textStreet = ViewBindings.findChildViewById(rootView, id);
      if (textStreet == null) {
        break missingId;
      }

      id = R.id.textSurname;
      TextView textSurname = ViewBindings.findChildViewById(rootView, id);
      if (textSurname == null) {
        break missingId;
      }

      id = R.id.textViewAddressHeader;
      TextView textViewAddressHeader = ViewBindings.findChildViewById(rootView, id);
      if (textViewAddressHeader == null) {
        break missingId;
      }

      id = R.id.textViewCity;
      TextView textViewCity = ViewBindings.findChildViewById(rootView, id);
      if (textViewCity == null) {
        break missingId;
      }

      id = R.id.textViewComment;
      TextView textViewComment = ViewBindings.findChildViewById(rootView, id);
      if (textViewComment == null) {
        break missingId;
      }

      id = R.id.textViewDate;
      TextView textViewDate = ViewBindings.findChildViewById(rootView, id);
      if (textViewDate == null) {
        break missingId;
      }

      id = R.id.textViewFlat;
      TextView textViewFlat = ViewBindings.findChildViewById(rootView, id);
      if (textViewFlat == null) {
        break missingId;
      }

      id = R.id.textViewHouse;
      TextView textViewHouse = ViewBindings.findChildViewById(rootView, id);
      if (textViewHouse == null) {
        break missingId;
      }

      id = R.id.textViewLevel;
      TextView textViewLevel = ViewBindings.findChildViewById(rootView, id);
      if (textViewLevel == null) {
        break missingId;
      }

      id = R.id.textViewName;
      TextView textViewName = ViewBindings.findChildViewById(rootView, id);
      if (textViewName == null) {
        break missingId;
      }

      id = R.id.textViewObject;
      TextView textViewObject = ViewBindings.findChildViewById(rootView, id);
      if (textViewObject == null) {
        break missingId;
      }

      id = R.id.textViewPeriod;
      TextView textViewPeriod = ViewBindings.findChildViewById(rootView, id);
      if (textViewPeriod == null) {
        break missingId;
      }

      id = R.id.textViewPhone;
      TextView textViewPhone = ViewBindings.findChildViewById(rootView, id);
      if (textViewPhone == null) {
        break missingId;
      }

      id = R.id.textViewPrice;
      TextView textViewPrice = ViewBindings.findChildViewById(rootView, id);
      if (textViewPrice == null) {
        break missingId;
      }

      id = R.id.textViewRentHeader;
      TextView textViewRentHeader = ViewBindings.findChildViewById(rootView, id);
      if (textViewRentHeader == null) {
        break missingId;
      }

      id = R.id.textViewResidentHeader;
      TextView textViewResidentHeader = ViewBindings.findChildViewById(rootView, id);
      if (textViewResidentHeader == null) {
        break missingId;
      }

      id = R.id.textViewSecondName;
      TextView textViewSecondName = ViewBindings.findChildViewById(rootView, id);
      if (textViewSecondName == null) {
        break missingId;
      }

      id = R.id.textViewStreet;
      TextView textViewStreet = ViewBindings.findChildViewById(rootView, id);
      if (textViewStreet == null) {
        break missingId;
      }

      id = R.id.textViewSurname;
      TextView textViewSurname = ViewBindings.findChildViewById(rootView, id);
      if (textViewSurname == null) {
        break missingId;
      }

      return new ActivityResidentInfoBinding((ConstraintLayout) rootView,
          backButtonFromResidentActivity, buttonAdd, textCity, textComment, textDate, textFlat,
          textHouse, textLevel, textName, textObject, textPeriod, textPhone, textPrice,
          textSecondName, textStreet, textSurname, textViewAddressHeader, textViewCity,
          textViewComment, textViewDate, textViewFlat, textViewHouse, textViewLevel, textViewName,
          textViewObject, textViewPeriod, textViewPhone, textViewPrice, textViewRentHeader,
          textViewResidentHeader, textViewSecondName, textViewStreet, textViewSurname);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
