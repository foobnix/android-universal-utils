# Android Universal Utils
This project for every developer for simplify daily work.

The main ideas:
* Nice code
* Less words
* Fast development
* Fan

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
##Views
```java
//before
TextView textView = (TextView) parentView.findViewById(R.id.text1);
textView.setText("Hello World");

//now
Views.text(parentView, R.id.text1, "Hello World");
Views.htmlText(parentView, R.id.text1, "<b>Hello World<b/>");

Views.goneAll(R.id.text1, R.id.text2, R.id.button3);
```
##Vh
ViewHolder pattern for Base Adapter, link http://www.piwai.info/android-adapter-good-practices/
```java
View view = convertView != null ? convertView : inflater.inflate(R.layout.details, null);

Vh.text(view, R.id.email, user.getEmail());
Vh.text(view, R.id.name, user.getCustomerName());
```