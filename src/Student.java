import java.io.Serializable;

public class Student implements Cloneable, Serializable{
    String nazwaStudenta;

    public Student(String nazwaStudenta){
        this.nazwaStudenta=nazwaStudenta;
    }
    public Student(Student student){
        this(student.getNazwaStudenta());

    }
    public Student(){
        Student student=new Student("nazwaStudenta");
        this.nazwaStudenta=student.nazwaStudenta+java.lang.System.identityHashCode(student);
    }

    public String getNazwaStudenta (){
        return nazwaStudenta;
    }

    @Override
    protected Object clone(){
        try {
            return (Student) super.clone();
        }
        catch (CloneNotSupportedException e){
            return new Student(this.nazwaStudenta);
        }
    }

    public void setNazwaStudenta (String nazwaStudenta){
        this.nazwaStudenta = nazwaStudenta;
    }

    @Override
    public String toString (){
        return "Nazwa studenta " + nazwaStudenta;
    }
}
