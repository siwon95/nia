function photoEditorUpload(){getFlashObject("\x49\x6d\x61\x67\x65\x45\x64\x69\x74\x6f\x72").upload();};function photoEditorCancel(){getFlashObject("\x49\x6d\x61\x67\x65\x45\x64\x69\x74\x6f\x72").close();};var pe_aJq=true;function photoEditorImageUploadCompleteHandler(result,pe_byg,pe_bxF,pe_blR,pe_xF){pe_aJq=false;eval("\x76\x61\x72\x20\x70\x65\x5f\x66\x73\x20\x3d\x20"+pe_xF);var pe_bjK="\x63\x6c\x6f\x73\x65";if(pe_blR>1&&pe_byg+pe_bxF!=pe_blR)pe_bjK="\x63\x6f\x6e\x74\x69\x6e\x75\x65";opener.setInsertImageFile(pe_fs.result,pe_fs.addmsg,pe_bjK);pe_aJq=true;};function photoEditorSlideshowUploadCompleteHandler(result,pe_xF,flashVars){if(result=="\x73\x75\x63\x63\x65\x73\x73"){var addmsg={};addmsg.imageURL=opener.editorBaseURL+opener.NamoSE.pe_fC.pe_byE;addmsg.imageTitle="\x73\x6c\x69\x64\x65\x73\x68\x6f\x77";addmsg.imageKind="\x70\x68\x6f\x74\x6f\x45\x64\x69\x74\x6f\x72\x53\x6c\x69\x64\x65\x73\x68\x6f\x77";addmsg.imageWidth="\x38\x30\x30";addmsg.imageHeight="\x36\x30\x30";addmsg.imageOrgPath="";addmsg.flashVars=(typeof flashVars=="\x73\x74\x72\x69\x6e\x67")?flashVars:"";addmsg.editorFrame=opener.pe_btg;opener.setInsertImageFile(result,addmsg);}else{eval("\x76\x61\x72\x20\x70\x65\x5f\x66\x73\x20\x3d\x20"+pe_xF);opener.setInsertImageFile(pe_fs.result,pe_fs.addmsg);}return;};var pe_bqE;function closePhotoEditor(){var pe_bvI=function(){if(pe_aJq)window.close();};var pe_bAO=function(){window.clearInterval(pe_bqE);window.close();};window.setTimeout(pe_bAO,1000);pe_bqE=window.setInterval(pe_bvI,50);};function getFlashObject(pe_bmW){if(navigator.appName.indexOf("\x4d\x69\x63\x72\x6f\x73\x6f\x66\x74")!= -1&&parseInt(navigator.userAgent.toLowerCase().match(/msie (\d+)/)[1],10)<9){return window[pe_bmW];}else{return document[pe_bmW];}}