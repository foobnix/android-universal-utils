# Android Universal Utils
This project for every Android developer for simplify daily routine work.

The main ideas:
* Nice code, Less words, Fast development, Fan
* Generate Java beans from JSON, Generate Parcelable  
[Json-To-Java-Model Generator](http://htmlpreview.github.io/?https://raw.githubusercontent.com/foobnix/android-universal-utils/master/json/generator.html)  
[Parcelable Generator](http://htmlpreview.github.io/?https://raw.githubusercontent.com/foobnix/android-universal-utils/master/json/generator.html)  
**input JSON:**  
```json
{
  "dealerId": 1,
  "userName": "First Second Name",
}
```
**Result Java Bean:**
```java
private int dealerId; //or private Integer dealerId
private String userName;
```
### Download 
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
## Views
```java
//before
TextView textView = (TextView) parentView.findViewById(R.id.text1);
textView.setText("Hello World");

//now
Views.text(parentView, R.id.text1, "Hello World");
Views.htmlText(parentView, R.id.text1, "<b>Hello World<b/>");
TextView name = Views.text(parentView, R.id.textName, "Name");

Views.goneAll(R.id.text1, R.id.text2, R.id.button3);
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





