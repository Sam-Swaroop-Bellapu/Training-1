import java.util.*;
import java.util.stream.Collectors;

import model.StudentFee;
import model.StudentMarks;
import model.Subjects;

public class student {
    private String rno;
    private String name;
    private String branch;
    private String year;
//    private List<StudentFee> sf;
//    private List<Subjects> su;
//    private List<StudentMarks> sm;

    public student(String rno, String name, String branch, String year) {
        this.rno = rno;
        this.name = name;
        this.branch = branch;
        this.year = year;
//        this.su=su;
//        this.sf=sf;
//        this.sm=sm;
    }

    public String getRno() {
        return rno;
    }

    public void setRno(String rno) {
        this.rno = rno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "student{" +
                "rno='" + rno + '\'' +
                ", name='" + name + '\'' +
                ", branch='" + branch + '\'' +
                ", year='" + year + '\'' +
                '}';
    }

    public static void main(String [] args){
        List<StudentFee> studentFees = new ArrayList<>();
        StudentFee sf1=new StudentFee("roll1",2000,"1/10/21");
        StudentFee sf2=new StudentFee("roll2",4000,"2/10/21");
        StudentFee sf3=new StudentFee("roll3",8000,"3/12/21");
        StudentFee sf4=new StudentFee("roll4",9000,"4/12/21");
        StudentFee sf5=new StudentFee("roll5",10000,"5/12/21");

        studentFees.add(sf1);
        studentFees.add(sf2);
        studentFees.add(sf3);
        studentFees.add(sf4);
        studentFees.add(sf5);

        List<Subjects> sub= new ArrayList<>();
        Subjects sub1= new Subjects("subject1","English");
        Subjects sub2=new Subjects("subject2","Physics");
//        Subjects sub3=new Subjects("subject3","Maths");

        sub.add(sub1);
        sub.add(sub2);
//        sub.add(sub3);

        List<StudentMarks> smarks=new ArrayList<>();

        StudentMarks smarks1=new StudentMarks("roll1","first","subject1","80");
        StudentMarks smarks2=new StudentMarks("roll1","first","subject2","76");

        StudentMarks smarks3=new StudentMarks("roll2","second","subject1","84");
        StudentMarks smarks4=new StudentMarks("roll2","second","subject2","89");

        StudentMarks smarks5=new StudentMarks("roll3","first","subject1","85");
        StudentMarks smarks6=new StudentMarks("roll3","first","subject2","90");

        StudentMarks smarks7=new StudentMarks("roll4","second","subject1","94");
        StudentMarks smarks8=new StudentMarks("roll4","second","subject2","92");

        StudentMarks smarks9=new StudentMarks("roll5","first","subject1","98");
        StudentMarks smarks10=new StudentMarks("roll5","first","subject2","99");

        smarks.add(smarks1);
        smarks.add(smarks2);
        smarks.add(smarks3);
        smarks.add(smarks4);
        smarks.add(smarks5);
        smarks.add(smarks6);
        smarks.add(smarks7);
        smarks.add(smarks8);
        smarks.add(smarks9);
        smarks.add(smarks10);



        List<student> stu=new ArrayList<>();
        student stu1=new student("roll1","abc","CSE","first");
        student stu2=new student("roll2","def","ECE","second");
        student stu3=new student("roll3","ghi","EEE","first");
        student stu4=new student("roll4","jkl","MME","second");
        student stu5=new student("roll5","mno","IT","first");


        stu.add(stu1);
        stu.add(stu2);
        stu.add(stu3);
        stu.add(stu4);
        stu.add(stu5);

        System.out.println("TASK-1   The first year students are:");
        for( student i:stu){

            if(i.getYear()=="first"){
                System.out.println(i.getRno()+":"+i.getName()+","+i.getBranch());

            }

        }

        System.out.println("-----------------  TASK-2  --------------------");
        for(student j:stu){
            for(StudentFee k:studentFees){
                if(j.getRno()==k.getRno())
                System.out.println("Roll Number:"+j.getRno()+", Name:"+j.getName()+", branch:"+j.getBranch()+", amount:"+k.getAmount()+", date:"+k.getDate());
            }

        }

        List<String> res=new ArrayList<>();

        System.out.println("-----------------  TASK-3  --------------------");
//        for(student l:stu){
//
//            for(StudentMarks m:smarks){
////                System.out.println("Roll Number:"+m.getRno()+", Name:"+l.getName());
//
//                for(Subjects n:sub){
//                    if(m.getSubjectNo()==n.getSubjectNo()){
//                        //res.add("Roll Number:"+m.getRno()+", Name:"+l.getName()+", subject name:"+n.getSubjectName()+", marks:"+m.getMarks());
//
//                        System.out.println("Roll Number:"+m.getRno()+", Name:"+l.getName()+" subject name:"+n.getSubjectName()+", marks:"+m.getMarks());
//
//                    }
//
//
//                }

//
//
//            }
//            break;
//        }

//        for (StudentMarks st:smarks){
////            System.out.println(st.getSubjectNo().getsubjectNo+":"+st.getMarks());
//
//            for(Subjects sm:st.getSubjectNo()){
////                System.out.println(st.getRno()+":");
////                if (st.getSubjectNo().equals(sm.getSubjectNo())){
//                    System.out.println(sm.getSubjectName()+":"+st.getMarks());
//
////                }
//            }
//        }

        for (student st:stu){
            System.out.println("Student Roll no.:"+st.getRno()+"   "+"Student Name:"+st.getName());

            for(StudentMarks sm:smarks){
                if(st.getRno()==sm.getRno()){

                    for (Subjects su:sub){
                        if(su.getSubjectNo()==sm.getSubjectNo()){
                            System.out.println(su.getSubjectName()+":"+sm.getMarks());
                        }

                }


                }
            }
        }







        System.out.println("-----------------  TASK-4  --------------------");

//        for(student l:stu){
//            for(StudentMarks m:smarks){
//                for(Subjects n:sub){
//                    if( l.getYear()=="first"){
//                        res.add("Roll Number:"+m.getRno()+", Name:"+l.getName()+", subject name:"+n.getSubjectName()+", marks:"+m.getMarks());
//
//                        System.out.println("Roll Number:"+m.getRno()+", Name:"+l.getName()+", subject name:"+n.getSubjectName()+", marks:"+m.getMarks());
//                        break;
//                    }
//
//
//
//                }
//
//
//
//            }

//        for (student v:stu){
//            System.out.println(v);
//
//
//        }
//        System.out.println(stu.stream().max());

//        List v=new ArrayList();
//        for (student d:stu){
//            for (StudentMarks f:smarks){
//
//            }
//        }

        class Sortbyyear implements Comparator<student> {
            // Used for sorting in ascending order of
            // name
            public int compare(student a, student b)
            {
                return a.getYear().compareTo(b.getYear());
            }
        }

        Collections.sort(stu, new Sortbyyear());

        for (int i = 0; i < stu.size(); i++)
            System.out.println(stu.get(i));

        System.out.println();
        System.out.println();
        for (student st:stu){
            System.out.println("Student Roll no.:"+st.getRno()+"   "+"Student Name:"+st.getName());

            for(StudentMarks sm:smarks){
                if(st.getRno()==sm.getRno()){

                    for (Subjects su:sub){
                        if(su.getSubjectNo()==sm.getSubjectNo()){
                            System.out.println(su.getSubjectName()+":"+sm.getMarks());
                        }

                    }


                }
            }
        }



















    }









}













