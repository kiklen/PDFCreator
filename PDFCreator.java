package pdfmanager;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;

public class PDFCreator {

    private String dest;
    private PDFSource source;
    private Document document;
    private FileOutputStream outFile;
    private Chunk chunk;
    private Font titleFont;
    private Font paragraphFont;
    private Paragraph paragraph [];
    private Image image;
    private TextManager text;
    private String line;

    public PDFCreator() {
    }

    public PDFCreator(String dest, String source) {
        this.dest = dest;
        document = new Document();
        this.source = new PDFSource();
        titleFont
              = FontFactory.getFont(FontFactory.HELVETICA, 30, Font.BOLDITALIC);
        paragraphFont
                = FontFactory.getFont(FontFactory.HELVETICA, 12, Font.NORMAL);
        text = new TextManager();
        try {
            text.bundle(source);
        } catch (Exception e) {
            System.out.println("no se encontro");
        }
        paragraph= new Paragraph[200];
    }

    public void create() throws IOException, DocumentException {
        outFile = new FileOutputStream(dest);
        PdfWriter.getInstance(document, outFile);
        document.open();
        String currentLine[]= new String[2000];
        int pointer =0, subtitle=0, marker=0;
        while (text.isThereAParagraph()) {
            currentLine[pointer] =   text.readParagraph();
            if (source.startInstruction(currentLine[pointer]).equals("{T}")) {
                    paragraph[pointer] = new Paragraph(source.line
                                            (currentLine[pointer]), titleFont);
                    paragraph[pointer].setAlignment(Element.TITLE);
                    document.add(paragraph[pointer]);
                    pointer++;
            }else if(source.startInstruction(currentLine[pointer]).
                    equals("{P}")){
                    
                    paragraph[pointer] = new Paragraph(source.line(currentLine
                            [pointer]), paragraphFont);
                    document.add(paragraph[pointer]);
                    while (!source.nextInstruction(currentLine[pointer]).equals
                            ("{#P}")) {
                        line=source.nextInstruction(currentLine[pointer]);
                        if (line.equals("{b}")) {
                                paragraphFont=FontFactory.getFont(
                                        FontFactory.HELVETICA, 12, Font.BOLD);
                                chunk= new Chunk(source.nextLine(currentLine
                                        [pointer]),paragraphFont);
                                document.add(chunk);
                        }else if(line.equals("{i}")){
                                paragraphFont=FontFactory.getFont(
                                        FontFactory.HELVETICA, 12, Font.ITALIC);
                                chunk= new Chunk(source.nextLine(currentLine
                                        [pointer]),paragraphFont);
                                document.add(chunk);
                        }else if(line.equals("{u}")){
                                paragraphFont=FontFactory.getFont(
                                        FontFactory.HELVETICA, 12,
                                        Font.UNDERLINE);
                                chunk= new Chunk(source.nextLine(currentLine
                                        [pointer]),paragraphFont);
                                document.add(chunk);
                        }else if(line.equals("{n}")){
                                paragraphFont=FontFactory.getFont(
                                        FontFactory.HELVETICA, 12, Font.NORMAL);
                                chunk= new Chunk(Chunk.NEWLINE+"");
                                document.add(chunk);
                        }else{
                            
                                paragraphFont=FontFactory.getFont(
                                        FontFactory.HELVETICA, 12, Font.NORMAL);
                                chunk= new Chunk(source.nextLine(currentLine
                                        [pointer]),paragraphFont);
                                document.add(chunk);
                        }
                    }
                    pointer++;
                    
                }else if(source.startInstruction(currentLine[pointer])
                        .equals("{I}")){
                    image = Image.getInstance(source.line
                                              (currentLine[pointer]));
                    document.add(image);
                }else if(source.startInstruction(currentLine[pointer])
                                            .equals("{C}")){
                    paragraph[pointer] = new Paragraph(source.line
                                        (currentLine[pointer]), paragraphFont);
                    document.add(paragraph[pointer]);
                    pointer++;
                }else {
                    paragraph[pointer]=new Paragraph(++marker+"\n"+(++subtitle)+
                            source.line(currentLine[pointer]),paragraphFont);
                    document.add(paragraph[pointer]);
                    pointer++;
                }
                source.setPointer(0);
                source.setPhrase("");
            }
            document.close();
        }

}
