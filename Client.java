public class Client {

        private String id;
        private String name;
        private String email;  
        private String contactNum;
        
        
        public Client (String id, String name, String email, String contactNum){
            this.name = name;
            this.email = email;
            this.contactNum = contactNum;
            this.id = id;
        }

        public String getName(){return name;}
        public String getEmail(){return email;}
        public String getContactNum(){return contactNum;}
        public String getId(){return id;}

        public String toFileString(){
            return id + " | " + name + " | " + email + " | " + contactNum; 
        }

}
