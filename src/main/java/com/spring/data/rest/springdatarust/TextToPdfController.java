package com.spring.data.rest.springdatarust;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
public class TextToPdfController {
        @PostMapping("/texToPdf")
        public ResponseEntity<?> getPdfToTextResponse(@RequestBody PdfToTextDto dto) throws IOException {
            return ResponseEntity.ok(getTextToPdf(dto));
        }
        private TextToPdfResponse getTextToPdf(PdfToTextDto dto) throws IOException {
            if(!List.of("en","te","kn","tm","hi").contains(dto.getLang())){
                return invalidResponse();
            }
            String dir = System.getProperty("user.dir");
            String path=dir+"/text.pdf";

            PdfWriter pdfWriter=new PdfWriter(path);

            PdfDocument pdfDocument=new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();
            Document document=new Document(pdfDocument);
            PdfFont fontLanguage=null;
            if(dto.getLang().equals("en")){
                if(checkLanguage(dto.getContent(), dto.getLang())){
                    return invalidResponse();
                }
            }
            String fontPath=dir+"/src/main/resources";
            if(dto.getLang().equals("hi")) { // hindi
                if(checkLanguage(dto.getContent(), dto.getLang())){
                    return invalidResponse();
                }

                fontLanguage = PdfFontFactory.createFont(fontPath+"/NotoSans-Regular.ttf");
                // NotoSans-Black.ttf
            }
            if(dto.getLang().equals("kn")){ // karnataka
                if(checkLanguage(dto.getContent(), dto.getLang())){
                    return invalidResponse();
                }
                fontLanguage=PdfFontFactory.createFont(fontPath+"/NotoSerifKannada-VariableFont_wght.ttf");
            }
            if(dto.getLang().equals("te")){ // telugu
                if(checkLanguage(dto.getContent(), dto.getLang())){
                    return invalidResponse();
                }
                fontLanguage=PdfFontFactory.createFont(fontPath+"/gautami.ttf");
            }
            Paragraph paragraph=new Paragraph();
            paragraph.setFontSize(10);
            if(dto.getLang().equalsIgnoreCase("en")){
                paragraph.add(dto.getContent());
            }else {
                paragraph.add(dto.getContent()).setFont(fontLanguage);
            }

            document.add(paragraph);
            document.close();
            return TextToPdfResponse.builder()
                    .success("1")
                    .message("successfully created the pdf")
                    .data(path)
                    .build();
    }

    private Boolean checkLanguage(String text, String language){
            boolean isLanguageCheck=false;
           if(language.equalsIgnoreCase("te")) {
               for (int i = 0; i < text.length(); i++) {
                   char c = text.charAt(i);
                   if (!((3072 <= c && c <= 3199) || (c <= 1023 && c > 0))) {
                       isLanguageCheck= true;
                   }
               }
           }
           if (language.equalsIgnoreCase("kn")){
               for (int i = 0; i < text.length(); i++) {
                   char c = text.charAt(i);
                   if (!((3327 >= c && c >= 3200) || (c <= 1023 && c >0))) {
                       isLanguageCheck= true;
                   }
               }
           }
           if(language.equalsIgnoreCase("hi")){ //2304-2431
               for (int i = 0; i < text.length(); i++) {
                   char c = text.charAt(i);
                   if (!((2304 <= c && c <= 2431) || (c <= 1023 && c >0))) {
                       isLanguageCheck= true;
                   }
               }
           }
           if(language.equalsIgnoreCase("en")){
               for (int i = 0; i < text.length(); i++) {
                   char c = text.charAt(i);
                   if (!(c <= 1023 && c >0)) {
                       isLanguageCheck= true;
                   }
               }
           }
           return isLanguageCheck;
    }
    private TextToPdfResponse invalidResponse(){
      return  TextToPdfResponse.builder()
                .success("0")
                .message("Error in generating pdf as language is wrong")
                .build();
    }
}
