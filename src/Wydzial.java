import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;

public class Wydzial implements Cloneable, Serializable{
    String nazwaWydzial;
    ArrayList<Student>studenci;
    public Wydzial(String nazwaWydzial){
        this.nazwaWydzial=nazwaWydzial;
        studenci=new ArrayList<>();
    }
    public Wydzial(Wydzial wydzial){
        ArrayList<Student>studenciKopia=new ArrayList<>();
        for (int i=0; i<wydzial.studenci.size(); i++){
            studenciKopia.add(new Student(wydzial.studenci.get(i)));
        }
        this.nazwaWydzial=wydzial.getNazwaWydzial();
        this.studenci=studenciKopia;
    }
    public Wydzial(int liczbaStudentow, int wydzialID){
        this.nazwaWydzial="nazwaWydzialu" + wydzialID;
        studenci=new ArrayList<>();
        for (int i=0; i<liczbaStudentow; i++){
            studenci.add(new Student());
        }
    }

    @Override
    protected Object clone (){
        Wydzial wydzial=null;
        ArrayList<Student> studenciKopia=new ArrayList<>();
        try{
            wydzial=(Wydzial) super.clone();
        }
        catch(CloneNotSupportedException e){
            wydzial= new Wydzial(this.nazwaWydzial, this.studenci);
        }
        for (int i=0; i<studenci.size(); i++){
            studenciKopia.add((Student)studenci.get(i).clone());
        }
        wydzial.studenci=studenciKopia;
        return wydzial;
    }

    public Wydzial (String nazwaWydzial, ArrayList<Student> studenci){
        this.nazwaWydzial = nazwaWydzial;
        this.studenci = studenci;
    }

    public String getNazwaWydzial (){
        return nazwaWydzial;
    }

    public void setNazwaWydzial (String nazwaWydzial){
        this.nazwaWydzial = nazwaWydzial;
    }

    public ArrayList<Student> getStudenci (){
        return studenci;
    }

    public void setStudenci (ArrayList<Student> studenci){
        this.studenci = studenci;
    }

    @Override
    public String toString (){
        return "\n"+"Nazwa wydzialu " + nazwaWydzial + "\n" + studenci;
    }
}
