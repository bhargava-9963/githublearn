package com.spring.data.rest.springdatarust;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import static com.itextpdf.tool.xml.css.CSS.Property.FONT;

@RestController
public class TextToPdfController {
        @PostMapping("/texToPdf")
        public ResponseEntity<?> getPdfToTextResponse(@RequestBody PdfToTextDto dto) throws DocumentException, IOException {
            return ResponseEntity.ok(getTextToPdf(dto));
        }
        private TextToPdfResponse getTextToPdf(PdfToTextDto dto) throws IOException, DocumentException {
            if(!List.of("en","te","kn","tm","hi").contains(dto.getLang())){
                return TextToPdfResponse.builder()
                        .success("0")
                        .message("Error in generating pdf as language is wrong")
                        .build();
            }
            Document document=new Document();
            String dir = System.getProperty("user.dir");
            String path=dir+"/text.pdf";
            PdfWriter.getInstance(document,new FileOutputStream(path));
            PdfWriter.getInstance(document, new FileOutputStream(path));
            if(dto.getLang().equalsIgnoreCase("hi")) {
                FontFactory.register("/Users/bhargavak/Documents/learning/springdatarust/src/main/resources/Mangal Regular.ttf");
            }
            if(dto.getLang().equalsIgnoreCase("te")){
                FontFactory.register("/Users/bhargavak/Documents/learning/springdatarust/src/main/resources/Ponnala Regular.otf");
            }
            if(dto.getLang().equalsIgnoreCase("kn")){
                FontFactory.register("/Users/bhargavak/Documents/learning/springdatarust/src/main/resources/TiroKannada-Regular.ttf");
            }

            document.open();
            Font f1 =FontFactory.getFont("Mangal", BaseFont.IDENTITY_H, true);
            Phrase p = new Phrase(dto.getContent(),f1);
            document.add(p);
            document.close();
            return TextToPdfResponse.builder()
                    .success("1")
                    .message("successfully created the pdf")
                    .data(path)
                    .build();
    }
}
