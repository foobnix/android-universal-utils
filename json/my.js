$(document).ready(function() {  

    var map = {};
    var lang = '';   
    
    function printVars(){
        
        for(key in map){
         lang += "private "+ map[key] + "  "+key+""+  ";<br/>";   
        }
       
    }
var fact = [    
    {
        type: "String",
        read :"{v} = in.readString();",
        write :"dest.writeString({v});"
    },
   
    {  
        type:"int",
        read :"{v} = in.readInt();",
        write :"dest.writeInt({v});"
    },
    {  
        type:"float",
        read :"{v} = in.readFloat();",
        write :"dest.writeFloat({v});"
    },
    
    {  
        type:"double",
        read :"{v} = in.readDouble();",
        write :"dest.writeDouble({v});"
    },
    {  
        type:"byte",
        read :"{v} = in.readByte();",
        write :"dest.writeByte({v});"
    },
     {  
        type:"long",
        read :"{v} = in.readLong();",
        write :"dest.writeLong({v});"
    },
     {  
        type:"boolean",
        read :"{v} = in.readByte() == (byte)0x01;",
         write :"dest.writeByte({v} == true?(byte) 0x01 : (byte)0x00);"
    },
     {  
        type:"Boolean",
         read :"byte t = in.readByte(); {v} = (t== (byte)0x02 ? null : t == (byte)0x01);",
         write :"dest.writeByte({v} == null ? (byte)0x02 : {v}== true ? (byte)0x01 : (byte)0x00);"
    },
     {
        type: "Integer",
         read :"{v} = in.readByte()==(byte)0x01?null:in.readInt();",
         write :"dest.writeByte({v}==null? (byte)0x01:(byte)0x00); if({v}!=null) dest.writeInt({v});"
    },
    {
        type: "Double",
         read :"{v} = in.readByte()==(byte)0x01?null:in.readDouble();",
         write :"dest.writeByte({v}==null? (byte)0x01:(byte)0x00); if({v}!=null) dest.writeDouble({v});"
    },
    {
        type: "Float",
         read :"{v} = in.readByte()==(byte)0x01?null:in.readFloat();",
         write :"dest.writeByte({v}==null? (byte)0x01:(byte)0x00); if({v}!=null) dest.writeFloat({v});"
    },
    {
        type: "Long",
         read :"{v} = in.readByte()==(byte)0x01?null:in.readLong();",
         write :"dest.writeByte({v}==null? (byte)0x01:(byte)0x00); if({v}!=null) dest.writeLong({v});"
    },
     {
        type: "Date",
         read :"{v} = in.readByte()==(byte)0x01?null:new Date(in.readLong());",
         write :"dest.writeByte({v}==null? (byte)0x01:(byte)0x00); if({v}!=null) dest.writeLong({v}.getTime());"
    },
]

    var other = {  
        type : "",
        read :"{v} = in.read???();",
        write :"dest.write???({v});"
    }

     
     function printParcable(){
          var read = "<br/><b>Parceble read </b><br/>";
            read += "public T(final Parcel in) {<br/>";
            
    
          var write = "<br/><b>Parceble write </b><br/>";
        write += '@Override <br/>\
    public void writeToParcel(final Parcel dest, final int flags) {</br>';
    
           for(name in map){       
               var javaType = map[name];
               var item = jQuery.grep(fact, function(it) {
                    return (it.type == javaType);
                })[0];
               if(!item){
                   item = other;
               }               
               read += item.read.replace(/{v}/g,name) + "<br/>";
               write += item.write.replace(/{v}/g,name)+ "<br/>";
               
           }
         read += "}</br>";
         read +='</br>public static final Parcelable.Creator CREATOR = new Parcelable.Creator() { <br/>\
        @Override <br/>\
        public T createFromParcel(final Parcel in) { <br/>\
            return new T(in); <br/>\
        } <br/>\
        <br/> \
        @Override <br/>\
        public Object[] newArray(final int size) { <br/>\
            return new T[size]; <br/>\
        } <br/>\
    };<br/>'; 
         
                  write += "}</br>"
                  
                  
         lang += read;             
         lang += write;
         
     }
        
        
      
    function proccess(){
      lang = '';
        map = {};
      var jsonp = $('#mytextarea1').val();
    
      var obj = $.parseJSON(jsonp);
        
      
      $.each(obj, function(key,value) {
          var jsType = jQuery.type(value);
          var jsValue = value.toString();
          var jsKey = key;
          
          var javaKey = key;
          var javaType = "";
          if(jsType == "string"){
              javaType = "String";
              if(jsKey.toLowerCase().indexOf("date")>=0){
                    javaType = "Date";    
                 }              
          }else if(jsType == "number"){
              if(jsValue.indexOf(".")>=0){
                  javaType = "Double";
              }else{
                  javaType = "Integer";
              }
          }else if(jsType =="boolean"){
                  javaType = "Boolean";
          }
          map[javaKey]=javaType                
      });     
    }
    function process1(){
        lang = '';
        map = {};
        var text = $('#mytextarea2').val();
        $.each( text.split(";"), function(index, chunk) {
           var line = chunk.replace("private","").replace("final","").replace("public","").replace("protected","").replace(";","").trim();
           var sub = line.split(" ");
            
               var type = sub[0];
               var name = sub[1];
            if(name && type){
                map[name.trim()]=type.trim();
            }
            
            
        });        
           }
       
     $("#button1").click(function(){
             proccess();
             printVars();
             printParcable();
         $('span').html(lang);                 
    });
    
    $("#button2").click(function(){
        process1();
        printParcable();
        $('span').html(lang);                 
    });
    
});
