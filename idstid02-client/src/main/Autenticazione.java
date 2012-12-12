package main;

import java.security.MessageDigest;

public class Autenticazione {
        private static Sessione sessione;

        public Autenticazione() {
                sessione = null;
        }
       
        public static Sessione getSessione() {
                return sessione;
        }

        public static void setSessione(Sessione session) {
                sessione = session;
        }
       
        public static String getMD5Sum(char[] password) {
            try {
                String psw = new String(password);
                MessageDigest md  = MessageDigest.getInstance("MD5");
                byte[] message = md.digest(psw.getBytes());
                StringBuffer sb = new StringBuffer();
                for (int i = 0; i < message.length; i++) {
                    sb.append(toHexString(message[i]));
                }
                return sb.toString();
            }
            catch(Exception e) {
                e.printStackTrace();
                return "";
            }
        }
       
        private static String toHexString(byte b) {
            String s = Integer.toHexString(0xff & b);
            if (s.length() < 2) {
                    s = "0" + s;
            }
            return s;
        }
}
