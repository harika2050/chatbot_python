package mypack;
import java.text.ParseException;
import java.util.*;
public class admin {

        public admin() {
        }
        public String aid;
        public String firstName;
        public String middleName;
        public String lastName;
        public String email;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAid() {
            return aid;
        }

        public void setAid(String aid) {
            this.aid = aid;
        }

        public String pass;
        public String getPass() {
            return pass;
        }

        public void setPass(String pass) {
            this.pass = pass;
        }
        public void addDetails() throws ParseException {
            Scanner sc= new Scanner(System.in);
            System.out.println("Which mode of travel details do you want to add[flight/train/bus]");
            String trans_details= sc.nextLine();
            if(trans_details.equals("train"))
            {
                Train ob= new Train();
                ob.addTrain();
            }
            if(trans_details.equals("flight"))
            {
                Flight ob= new Flight();
                ob.addFlight();
            }
            if(trans_details.equals("bus"))
            {
                Bus ob= new Bus();
                ob.addBus();
            }
            // TODO implement here
        }
        public void modifyDetails() {
            Scanner sc= new Scanner(System.in);
            sc.nextLine();
            System.out.println("Do you want to delete any entry[y/n]?");
            char ans= sc.next().charAt(0);
            if(ans=='y')
            {
                sc.nextLine();
                System.out.println("Which mode of travel details do you want to delete[flight/train/bus]");
                String trans_details= sc.nextLine();
                if(trans_details.equals("train"))
                {
                    Train ob= new Train();
                    ob.deleteTrain();
                }
                if(trans_details.equals("flight"))
                {
                    Flight ob= new Flight();
                    ob.deleteFlight();
                }
                if(trans_details.equals("bus"))
                {
                    Bus ob= new Bus();
                    ob.deleteBus();
                }
            }
            System.out.println("Do you want to modify any entry[y/n]");
            ans= sc.next().charAt(0);
            if(ans=='y')
            {
                sc.nextLine();
                System.out.println("Which mode of travel details do you want to modify[flight/train/bus]");
                String trans_details= sc.nextLine();
                if(trans_details.equals("train"))
                {
                    Train ob= new Train();
                    ob.modifyTrain();
                }
                if(trans_details.equals("flight"))
                {
                    Flight ob= new Flight();
                    ob.modifyFlight();
                }
                if(trans_details.equals("bus"))
                {
                    Bus ob= new Bus();
                    ob.modifyBus();
                }
            }
            // TODO implement here
        }
        public void viewDetails() {
            Scanner sc= new Scanner(System.in);
            System.out.println("Which mode of travel details do you want to view[flight/train/bus]");
            String trans_details= sc.nextLine();
            if(trans_details.equals("train"))
            {
                Train ob= new Train();
                ob.viewTrain();
            }
            if(trans_details.equals("flight"))
            {
                Flight ob= new Flight();
                ob.viewFlight();
            }
            if(trans_details.equals("bus"))
            {
                Bus ob= new Bus();
                ob.viewBus();
            }
            // TODO implement here
        }

    }

