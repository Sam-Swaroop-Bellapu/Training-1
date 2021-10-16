import model.Benefits;
import model.Department;
import model.Employee;
import model.Location;
import java.util.*;


public class Streams {

    public static void main(String[] args){

        List<Location> la=new ArrayList<>();
        List<Location> lb=new ArrayList<>();
        List<Location> lc=new ArrayList<>();
        Location l1=new Location("lID1","Hyderabad","India");
        Location l2=new Location("lID2","Chennai","India");
        Location l3=new Location("lID3","London","United Kingdom");

        la.add(l1);
        lb.add(l2);
        lc.add(l3);


        List<Benefits> ba=new ArrayList<>();
        List<Benefits> bb=new ArrayList<>();
        List<Benefits> bc=new ArrayList<>();
        Benefits b1=new Benefits("BN1","Medical ","2 yrs of Medical Insurance");
        Benefits b2=new Benefits("BN2","Transport","Transport services available everyday");
        Benefits b3=new Benefits("BN3","Paid leaves","20 paid leaves");


        ba.add(b1);
        ba.add(b2);
        bb.add(b2);
        bb.add(b3);
        bc.add(b3);
        bc.add(b1);


        List<Department> dept=new ArrayList<>();
        List<Department> dept1=new ArrayList<>();

        Department d1=new Department("ID1","HealthCare");
        Department d2=new Department("ID2","Finance");
        Department d3=new Department("ID3","Admin");
        Department d4=new Department("ID4","Fraud");

        dept.add(d1);
//        dept1.add(d2);
        dept.add(d2);
        dept.add(d3);
        dept.add(d4);















        List<Employee> em=new ArrayList<>();
        Employee em1=new Employee("ID1","abc",2000,la,"ID1",null);
        Employee em2=new Employee("ID2","def",30000,lb,"ID2",bb);
        Employee em3=new Employee("ID3","ghi",40000,lc,"ID3",null);
        Employee em4=new Employee("ID4","jkl",50000,la,"ID4",bc);
        Employee em5=new Employee("ID5","mno",60000,lb,"ID1",bb);



        em.add(em1);
        em.add(em2);
        em.add(em3);
        em.add(em4);
        em.add(em5);


        System.out.println("--------------Task-1------------");

        System.out.println("The employees in India are:");
//        for(Employee emp:em){
//            for(Location lo:emp.getLocationId()){
//                if (lo.getLocationCountry()=="India"){
//                    System.out.println(emp.getEmployeeName());
//
//                }
//            }
//        }
        em.forEach(w->{
            w.getLocationId().forEach(q->{
                if (q.getLocationCountry()=="India"){
                    System.out.println(w.getEmployeeName());
                }
            });
        });




        System.out.println("-------------Task-2-----------");
        System.out.println("The employees in Hyderabad or Chennai are:");
//        for(Employee emp:em){
//            for(Location lo:emp.getLocationId()){
//                if (lo.getLocationName()=="Hyderabad" || lo.getLocationName()=="Chennai"){
//                    System.out.println(emp.getEmployeeName());
//
//                }
//            }
//        }
        em.stream().forEach(s->{
            s.getLocationId().forEach(d->{if(d.getLocationName()=="Hyderabad"||d.getLocationName()=="Chennai"){
                    System.out.println(s.getEmployeeName());}
            });
        });



        System.out.println("-------------Task-3-----------");
        System.out.println("The employees who have benefits are:");
        for(Employee emp1:em){
            if (emp1.getBenefitId()!=null){
                System.out.println();
                System.out.println("Employee name:"+emp1.getEmployeeName());
            }
            if(emp1.getBenefitId()!=null){
            for(Benefits be:emp1.getBenefitId()){
                    System.out.println(" "+"Benefit:"+be.getBenefitName()+" "+"Details:"+be.getDescription());
                }
            }
        }

        System.out.println("-----------Task-4------------");
//        for(Employee emp:em){
//            for(Department f:dept){
//
//                if(emp.getDeptId()==f.getDeptID()){
//                    System.out.println("Employee Name:"+emp.getEmployeeName()+" "+"DeptID:"+f.getDeptID()+" "+"Dept Name:"+f.getDeptName());
//
//                }
//
//            }
//        }
        em.forEach(x->{
            dept.stream().filter(y->x.getDeptId()==y.getDeptID()).forEach(d->{System.out.println("Employee Name:"+x.getEmployeeName()+" "+"DeptID:"+d.getDeptID()+" "+"Dept Name:"+d.getDeptName());});
        });
//        em.forEach(x->{
//            System.out.println("");
//            x.getDeptId().forEach(y->{
//                if(x.getDeptId().equals(y.getDeptID())){
//                    System.out.println(x.getEmployeeName()+":"+y.getDeptName());
//                }
//            });
//        });
//        em.forEach(w->{
//            dept.forEach(q->{
////                if (q.get()=="India"){
//                if(w.getDeptId()==(q.getDeptID())){
//                    System.out.println(w.getEmployeeName()+""+q.getDeptName());
//
//
//                }
//
////                }
//            });
//        });



        System.out.println("-----------Task-5------------");
        System.out.println("Employees who are in Chennai and India");
//        em.forEach(value->{
//            value.getLocationId().forEach(x->{
//                if (x.getLocationCountry()=="India" && x.getLocationName()=="Chennai"){
//                    System.out.println(value.getEmployeeName());
//                }
//            });
//
//        });
        em.stream().forEach(value->{
            value.getLocationId().stream().filter(s->s.getLocationCountry()=="India" && s.getLocationName()=="Chennai").forEach(d->{System.out.println(value.getEmployeeName());});
        });




















    }

}
