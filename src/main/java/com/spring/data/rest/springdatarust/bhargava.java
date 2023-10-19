package com.spring.data.rest.springdatarust;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.font.FontProvider;
import com.itextpdf.layout.font.FontSet;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.itextpdf.forms.xfdf.XfdfConstants.DEST;

public class bhargava {
    public static void main(String[] arg) throws IOException, SAXException, ParserConfigurationException {
        String dir = System.getProperty("user.dir");
        String fontPath=dir+"/src/main/resources";
        String html = "<html>\n" +
                "  <head>\n" +
                "        <meta charset='UTF-8'/>\n" +
                "        <style>\n" +
                "            body {font-family: Noto Sans Kannada;}\n" +
                "        </style>\n" +
                "  </head>\n" +
                "  <body>ಕನ್ನಡ</body>\n" +
                "  </html>";
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont("font/noto-kannada/NotoSansKannada-Regular.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        OutputStream out = new FileOutputStream(new File("so.pdf"));
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(out);
        out.close();
    }
}
