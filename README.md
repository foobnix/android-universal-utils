# Android Universal Utils
This project for every Android developer for simplify daily routine work.

### JSON to Java Online Generator
<a href="http://htmlpreview.github.io/?https://raw.githubusercontent.com/foobnix/android-universal-utils/master/json/generator.html" target="_blank">Json-To-Java-Model Generator</a>  
```java
//Json-To-Java-Model Generator
{
    "auctionHouse": "sample string 1",
    "bidDate": "2014-05-30T08:20:38.5426521-04:00 ",
    "bidPrice": 3,
    "bidPrice1": 3.1,
    "isYear":true
}

//Result Java Class with Fields
private String  auctionHouse;
private Date  bidDate;
private int  bidPrice;
private double  bidPrice1;
private boolean  isYear;

//JSONObject get (Optional)
auctionHouse = obj.getString("auctionHouse");
bidDate = obj.opt("bidDate");
bidPrice = obj.getInt("bidPrice");
bidPrice1 = obj.getDouble("bidPrice1");
isYear = obj.getBoolean("isYear");

//JSONObject put (Optional)
obj.put("auctionHouse",auctionHouse);
obj.put("bidDate",bidDate);
obj.put("bidPrice",bidPrice);
obj.put("bidPrice1",bidPrice1);
obj.put("isYear",isYear);
```
### Download latest Android Universal Utils .jar
[android-universal-utils.jar](https://github.com/foobnix/android-universal-utils/raw/master/lib/android-universal-utils.jar)
## LOG
Simple add as many parameters as you need, any type. "|" - delimiter for see white spaces in the end
```java

LOG.TAG = "DEBUG"; //Default
LOG.DELIMITER = "|"; //Default
LOG.isEnable = true; //Default

LOG.d("Hello", "Log ", 5, Long.valueOf(99l), new Date());
```
```
Hello|Log |5|99|Thu Jun 12 12:39:50 EET 2014|
```
## Resource Injector
Inject views by it names as id
```java
@ResId EditText editAddress1, editAddress2, editCompanyName;
@ResId Spinner spinnerCountry, spinnerState;
@ResId CheckBox checkPrivateSeller;
...

//Activate annotation in current Fragment
public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	View view = inflater.inflate(R.layout.my_account, container, false);
	ResInjector.inject(view, this, savedInstanceState);

	editAddress1.setText("Hello Android Reflection");
...
}

@ResIdOnClick(R.id.userProfile)
public void onProfile(View view) {//with View param
	...
}

@ResIdOnLongClick(R.id.buttonSave)
public void buttonSaveLongClick() {
	...
}

//Get Arguments
public static final String EXTRA_DEMO = "Demo_ARG";
@ExtraArgument(EXTRA_DEMO)
String helloWorld;

//EXTRA_ARGUMENT_1, EXTRA_ARGUMENT_2, EXTRA_ARGUMENT_3
bundle.putString(MyFragment.EXTRA_ARGUMENT_1, "YOOO");
fragment.setArguments(withSerializable);

@ExtraArgument_1
String myValue;



//Run this method every 1 second
@TickTimer(1000)
public void updateEndDate() {
...
}

//Save param state on rotation
@SaveState
private int timer = 0, currentTab = 0;

```
<<<<<<< HEAD
## ResInjector copy model into view or view into model
=======
## ResInjector map view
>>>>>>> refs/remotes/origin/master
```java
public class Account implements Serializable {
    @ResId(R.id.editAddress1) private String address1;
    @ResId(R.id.editAddress2) private String address2;
}

//populate views
ResInjector.copyModelToView(model, view);

//get Views values
ResInjector.copyViewToModel(view, model);
```

## Views
```java
//before
TextView textView = (TextView) parentView.findViewById(R.id.text1);
textView.setText("Hello World");

//now
Views.text(parentView, R.id.text1, "Hello World");

//html
Views.htmlText(parentView, R.id.text1, "<b>Hello World<b/>");

//multiple action
Views.gone(R.id.text1, R.id.text2, R.id.button3);
Views.unselect(R.id.select1, R.id.select2, R.id.select3);
```
## Vh
ViewHolder pattern for Base Adapter, link http://www.piwai.info/android-adapter-good-practices/
```java
View view = convertView != null ? convertView : inflater.inflate(R.layout.details, parent, false);

Vh.text(view, R.id.email, user.getEmail());
Vh.text(view, R.id.name, user.getCustomerName());
```

##Dips
```java
int px = Dips.dpToPx(10);
int dips = Dips.pxToDp(100);

int screenWidthPx = Dips.screenWidth(context);
int screenHeightPx = Dips.screenHeight(context);
```
## Internets
```java
boolean isOnline = Internets.isOnline(context);
boolean isOffline = Internets.isOffline(context);
```
## Keyboards
```java
Keyboards.hideAlways(context);
Keyboards.close(context);
```
## TxtUtils
```java
TxtUtils.nullToEmpty(null); // return "";
TxtUtils.nullNullToEmpty("null"); // return "";
TxtUtils.nullNullToEmpty("   "); // return "";
TxtUtils.isEmpty("   "); // return true;
TxtUtils.join(" ",1,2,"a","b"); // return "1 2 a b";
TxtUtils.join("|","a","b",1,2); // return "a|b|1|2";
TxtUtils.format$("My name is $first $second","a","b");//return "My name is a b";
```
##Apps
```java
Apps.getVersionName(contex);//example "2.0.0"
Apps.getVersionCode(contex);//example 23
Apps.getPackageName(contex);//example "com.foobnix.android.utils"
Apps.isPackageInstalled(context,"com.foobnix.android.utils") // return true;

```
##Open
```java
Open.browser(context,"http://www.ukr.net"); //open in external browser
Open.playMarket(context,"com.foobnix.pdf.reader"); //open in play market
Open.call(context,"123-123-213"); //call phone number
```
##Reflections
```java
Reflections.getFieldNameForValue("ACTION_", MotionEvent.class, 2) 
//return ACTION_MOVE, public static final int ACTION_MOVE = 2;

view.setOnDragListener(new OnDragListener() {

    @Override
    public boolean onDrag(View v, DragEvent event) {
        LOG.d("Drag Event", event.getAction(), Reflections.getFieldNameForValue("ACTION_", DragEvent.class, event.getAction()));
        return false;
    }
});
//Drag Event:|1|ACTION_DRAG_STARTED
//Drag Event:|4|ACTION_DRAG_ENDED
```
##Func
```java
Func.forAll(new ResultResponse<View>() {

    @Override
    public void onResultRecive(View view) {
        view.setOnTouchListener(onTouch);
    }

}, Views.findAll(this, R.id.button1, R.id.button2, R.id.button11, R.id.button21));

//With index
Func.forAll(new ResultIndexResponse<View>() {

    @Override
    public void onResultRecive(int pos, View view) {
        ((Button) view).setText("Button " + pos);
    }

}, Views.findAll(this, R.id.b1, R.id.b2, R.id.b3, R.id.b4, R.id.b5, R.id.b6, R.id.b7));

//find index
spinnerCountry.setSelection(Func.findIndex(new ResultResponse<Country>() {
    @Override
    public boolean onResultRecive(Country arg0) {
        return arg0.getId().equals(sellerAccount.getCountryId());
    }
}, AppState.get().getCountries()).second);

//filter lists
List<State> filtered = Func.filter(new ResultResponse<State>() {

                    @Override
                    public boolean onResultRecive(State arg0) {
                        return arg0.getCountryId().equals(country.getId());
                    }
                }, AppState.get().getStates());
```
## BaseItemAdapter<T>
```java
private BaseItemAdapter<SearchItem> adapter = new BaseItemAdapter<SearchItem>() {

@Override
public View getView(int possition, View convertView, ViewGroup parent, SearchItem item) {
    ...
    return view;
}

};


final BaseItemLayoutAdapter<State> stateAdapter = new BaseItemLayoutAdapter<State>(getActivity(), android.R.layout.simple_spinner_dropdown_item) {

    @Override
    public void populateView(View inflate, int arg1, State arg2) {
	Views.text(inflate, android.R.id.text1, arg2.getName());
	Views.text(inflate, android.R.id.text2, arg2.getDisplayName());
    }
};

```
## ResultResponse<T>
```java
//ResultResponse<T> - response listener for any type

ResultResponse<Token> token = new ResultResponse<Token>() {
            
        @Override
        public void onResultRecive(Token result) {
            
        }
};
```
## Application State Pattern
```java
public class AppState {
    private static AppState instance = new AppState();
    static SharedPreferences sharedPreferences;

    public SellerAccount sellerAccount;
    public UserProfile userProfile;

    private final Gson gson = new Gson();

    public static AppState get() {
        return instance;
    }

public synchronized void save() {
	sharedPreferences.edit()//
        .putString("sellerAccount", gson.toJson(sellerAccount, SellerAccount.class).toString())//
        .putString("userProfile", gson.toJson(userProfile, UserProfile.class).toString())//
        .commit();
}

public synchronized void load(Context context) {
	sharedPreferences = context.getSharedPreferences("myapp", Context.MODE_PRIVATE);
	sellerAccount = gson.fromJson(sharedPreferences.getString("sellerAccount", null), SellerAccount.class);
	userProfile = gson.fromJson(sharedPreferences.getString("userProfile", null), UserProfile.class);
	}
}

//Inside Application or Service
AppState.get().load(this);

//Save states
AppState.get().save();
```
##Model Example
```java
public class ExampleFragment extends ModelFragment<User> {

    @ResId  
    EditText textLogin, textPassword;

    @ResId 
    CheckBox checkboxAggrement;

    @ExtraArgument_1 
    int currentTab;

    @SaveState 
    boolean isSignAgreement;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        ResInjector.inject(view, this, savedInstanceState);

        activeTab(currentTab);
        populateModel();
        return view;
    }

    @ResIdOnClick(R.id.onLogin)
    public void onLogin() {
        saveModel();
        doLoginUser(model);
    }

    @Override
    public void populateModel() {
        checkboxAggrement.setChecked(isSignAgreement);
        textLogin.setText(model.getName());
    }

    @Override
    public void saveModel() {
        model.setName(textLogin.getText().toString());
    }

}
```
## Simple Fragment Example
```java
public class SimpleExampleFragment extends EmptyModelFragment {

    @ResId EditText textLogin, textPassword;

    @ExtraArgument_1 int currentTab;

    @SaveState boolean myValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_user, container, false);
        ResInjector.inject(view, this, savedInstanceState);

        return view;
    }

    @ResIdOnClick(R.id.onLogin)
    public void doAction() {

    }

}
```

    Copyright 2011-2014 Ivan Ivanenko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.





