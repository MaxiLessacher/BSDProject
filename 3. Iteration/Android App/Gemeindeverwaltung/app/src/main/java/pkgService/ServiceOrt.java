package pkgService;


import java.util.Vector;

import pkgClasses.Ort;

public class ServiceOrt {
    private Vector<Ort> orte = new Vector<Ort>();

    public ServiceOrt(){

    }

    public Vector<Ort> getOrte(){
        return this.orte;
    }

    public void addOrt(Ort tmp) {
        this.orte.addElement(tmp);
    }

    public void removeOrt(Ort ort){
        for(int i = 0; i< orte.size();i++){
            if(this.orte.get(i).compareTo(ort) == 0){
                this.orte.remove(i);
                break;
            }
        }
    }

    public void updateOrt(Ort alt, Ort neu){
        for(int i = 0; i< orte.size();i++){
            if(this.orte.get(i).compareTo(alt) == 0){
                this.orte.get(i).setOrt(neu.getOrt());
                this.orte.get(i).setPlz(neu.getPlz());
                break;
            }
        }
    }

    public int getSize() {
        return this.orte.size();
    }


}