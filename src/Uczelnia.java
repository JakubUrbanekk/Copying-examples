import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Uczelnia implements Cloneable, Serializable{
    String nazwaUczelnii;
    ArrayList<Wydzial> wydzialy;

    public Uczelnia(String nazwaUczelnii, int liczbaWydzialow, int liczbaStudentowWydzial){
        this.nazwaUczelnii = nazwaUczelnii;
        wydzialy = new ArrayList<>();
        for(int i = 0; i < liczbaWydzialow; i++){
            wydzialy.add(new Wydzial(liczbaStudentowWydzial, i));
        }
    }

    public Uczelnia (String nazwaUczelnii, ArrayList<Wydzial> wydzialy){
        this.nazwaUczelnii = nazwaUczelnii;
        this.wydzialy = wydzialy;
    }
    public Uczelnia (Uczelnia uczelnia){
        ArrayList<Wydzial> wydzialyKopia=new ArrayList<>();
        for (int i=0; i<uczelnia.wydzialy.size(); i++){
            wydzialyKopia.add(new Wydzial(uczelnia.wydzialy.get(i)));
        }
        this.wydzialy=wydzialyKopia;
        this.nazwaUczelnii =uczelnia.nazwaUczelnii;
    }

    public Uczelnia shallowCopy(){
        String nazwaUczelni=this.getNazwaUczelnii();
        ArrayList<Wydzial> wydzialy=this.getWydzialy();
        Uczelnia uczelniaKopia=new Uczelnia(nazwaUczelni, this.getWydzialy());
        return uczelniaKopia;
    }
    public Uczelnia deepCopyWithCopyConstructors(){
        return new Uczelnia(this);
    }

    @Override
    protected Object clone() {
        Uczelnia uczelnia=null;
        ArrayList<Wydzial> wydzialy=new ArrayList<>();
        try{
            uczelnia=(Uczelnia) super.clone();
        }
        catch(CloneNotSupportedException e){
            uczelnia=new Uczelnia(this.nazwaUczelnii,this.wydzialy);
        }
        for (int i=0; i<wydzialy.size(); i++){
            wydzialy.add((Wydzial)wydzialy.get(i).clone());
        }
        uczelnia.wydzialy=wydzialy;
        return uczelnia;
    }
    public Uczelnia serializationUtilsClone(){ //apache commons lang
        Uczelnia uczelniaCopy=null;
        uczelniaCopy=(Uczelnia) SerializationUtils.clone(this);
        return uczelniaCopy;
    }

    @Override
    public boolean equals (Object o){
        if(this == o) return true;
        if(!(o instanceof Uczelnia)) return false;
        Uczelnia uczelnia = (Uczelnia) o;
        if (!this.nazwaUczelnii.equals(uczelnia.nazwaUczelnii)){
            return false;
        }
        ArrayList<Student> studentciObject=null;
        ArrayList<Student> studentciThis=null;
        ArrayList<Wydzial>wydzialyObject=uczelnia.getWydzialy();
        int liczbaWydzialowObject=uczelnia.getWydzialy().size();
        int liczbaStudentowObject=0;
        int liczbaStudentowThis=0;
        if (liczbaWydzialowObject!=wydzialy.size()){
            return false;
        }

        for (int i=0; i<liczbaWydzialowObject; i++){
            if (!wydzialy.get(i).getNazwaWydzial().equals(wydzialyObject.get(i).getNazwaWydzial())){
                System.out.println("Zla nazwa wydzialu. Nazwa z pierwszego obiektu " + wydzialy.get(i).getNazwaWydzial() + " Nazwa z drugiego obiektu " + wydzialyObject.get(i).getNazwaWydzial()) ;
                return false;
            }
            studentciObject=wydzialyObject.get(i).studenci;
            studentciThis=wydzialy.get(i).studenci;
            liczbaStudentowObject=studentciObject.size();
            liczbaStudentowThis=studentciThis.size();
            if (liczbaStudentowObject!=liczbaStudentowThis){
                return false;
            }
            for (int j=0; j<liczbaStudentowObject; j++){
                if (!studentciObject.get(j).getNazwaStudenta().equals(studentciThis.get(j).getNazwaStudenta())){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode (){

        return Objects.hash(nazwaUczelnii, wydzialy);
    }

    public Uczelnia(){
        wydzialy=new ArrayList<>();
    }
    public Uczelnia(String nazwaUczelnii){
        this.nazwaUczelnii=nazwaUczelnii;
    }

    public String getNazwaUczelnii (){
        return nazwaUczelnii;
    }

    public void setNazwaUczelnii (String nazwaUczelnii){
        this.nazwaUczelnii = nazwaUczelnii;
    }

    public ArrayList<Wydzial> getWydzialy (){
        return wydzialy;
    }

    public void setWydzialy (ArrayList<Wydzial> wydzialy){
        this.wydzialy = wydzialy;
    }


    @Override
    public String toString (){
        return "Nazwa uczelnii " + nazwaUczelnii + wydzialy + "\n";
    }
}

