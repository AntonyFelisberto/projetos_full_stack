window.onload=function(){
    var largura = window. screen. width;
    if(largura>=1024 ){
      setTimeout(function(){
        var video=document.getElementById("toque");
        video.currentTime=0;
      },100);  
    }          
};


