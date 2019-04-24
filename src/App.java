import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;

import java.util.ArrayList;

public class App{
    public static void main (String[] args){
        int liczbaWydzialow=200;
        int liczbaStudentowWydzial=500;
        Uczelnia uczelnia=new Uczelnia("Uczelnia 1",liczbaWydzialow, liczbaStudentowWydzial);
      //  System.out.println(uczelnia);
        long startTime=System.nanoTime();
        Uczelnia shallowUczelnia=null;
        shallowUczelnia = uczelnia.shallowCopy();
        long endTime=System.nanoTime();
        long elapsedTime=(endTime-startTime);
        double seconds = (double)elapsedTime / 1_000_000_000.0;
        ArrayList<Double> times=new ArrayList<>();
        times.add(seconds);
      //  System.out.println("ShallowCopy uczelnia "+ shallowUczelnia);
        startTime=System.nanoTime();
        Uczelnia deepCopyWithCopyConstrucots=null;
        deepCopyWithCopyConstrucots = uczelnia.deepCopyWithCopyConstructors();
        endTime=System.nanoTime();
        elapsedTime=(endTime-startTime);
        seconds = (double)elapsedTime / 1_000_000_000.0;
        times.add(seconds);
      //  System.out.println("Deep copy with copy constructors "+ deepCopyWithCopyConstrucots);
        startTime=System.nanoTime();
        Uczelnia deepCopyWithCloneable=null;
        deepCopyWithCloneable = (Uczelnia) uczelnia.clone();
        endTime=System.nanoTime();
        elapsedTime=(endTime-startTime);
        seconds = (double)elapsedTime / 1_000_000_000.0;
        times.add(seconds);
     //   System.out.println("Deep Copy with cloenable interface "+deepCopyWithCloneable);
        startTime=System.nanoTime();
        Uczelnia serializableCopy=null;
        serializableCopy = uczelnia.serializationUtilsClone();
        endTime=System.nanoTime();
        elapsedTime=(endTime-startTime);
        seconds = (double)elapsedTime / 1_000_000_000.0;
        times.add(seconds);
      //  System.out.println("Deep copy with serializationUtils" + serializableCopy);

        System.out.println("Sprawzdanie kopii deepCopyWithCloneable " + uczelnia.equals(deepCopyWithCloneable));
        System.out.println("Sprawzdanie kopii serializableUtilsCopy " + uczelnia.equals(serializableCopy));
        System.out.println("Sprawzdanie kopii deep copy with constructors " + uczelnia.equals(deepCopyWithCopyConstrucots));
        System.out.println("Sprawzdanie kopii shallow copy " + uczelnia.equals(deepCopyWithCloneable));

        ArrayList<Wydzial> shallowWydzialy = shallowUczelnia.getWydzialy();
        shallowWydzialy.get(0).setNazwaWydzial("Shallow"); // zmiana nazwa wydzialu w kopii zmienia tez nazwe wydzialu w oryginale
        System.out.println("Sprawzdanie kopii shallow po zmianie nazwy wydzialu "+ uczelnia.equals(shallowUczelnia) + " Porownanie oryginalnu z kopia stworzona za pomoca glebokiego kopiowania " + uczelnia.equals(deepCopyWithCloneable));

        ArrayList<Wydzial> deepCopyWithCopyConstrucorsWydzialy=deepCopyWithCopyConstrucots.getWydzialy();
        deepCopyWithCopyConstrucorsWydzialy.get(0).setNazwaWydzial("Deep Copy"); // zmiana nazwy wydzialu w deepCopy nie zmienia nazwy wydzialu w oryginale
        System.out.println("Zmieniona nazwa wydzialu w deep copy " + deepCopyWithCloneable.equals(deepCopyWithCopyConstrucots));

        CategoryChart chart = new CategoryChartBuilder().width(1300).height(500).title("Liczba obiektów " +liczbaWydzialow+liczbaWydzialow*liczbaStudentowWydzial+1).xAxisTitle("Nazwa").yAxisTitle("Czas wykonania(s)").build();
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);
        ArrayList<String> sposobyKopiowania=new ArrayList<>();
        sposobyKopiowania.add("ShallowCopy");
        sposobyKopiowania.add("CopyConstructor");
        sposobyKopiowania.add("CloneableInterface");
        sposobyKopiowania.add("Apache Common lang");
        chart.addSeries(" ", sposobyKopiowania,times);
        new SwingWrapper(chart).displayChart();
    }
}
