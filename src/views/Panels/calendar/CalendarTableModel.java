package views.Panels.calendar;

import models.Event;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

public class CalendarTableModel extends AbstractTableModel {
    int nb_colonnes = 7;
    int nb_lignes = 5;
    String label;
    String titres[] = new String[7];
    Vector<Vector<String>> calendarValues = new Vector<Vector<String>>();
    
    public CalendarTableModel(GregorianCalendar cal) {
            titres[0] = "Dimanche";
            titres[1] = "Lundi";
            titres[2] = "Mardi";
            titres[3] = "Mercredi";
            titres[4] = "Jeudi";
            titres[5] = "Vendredi";
            titres[6] = "Samedi";
            
            String[] moisfr = new String[12];
            moisfr[0] = "Janvier";
            moisfr[1] = "Février";
            moisfr[2] = "Mars";
            moisfr[3] = "Avril";
            moisfr[4] = "Mai";
            moisfr[5] = "Juin";
            moisfr[6] = "Juillet";
            moisfr[7] = "Aout";
            moisfr[8] = "Septembre";
            moisfr[9] = "Octobre"; 
            moisfr[10] = "Novembre";
            moisfr[11] = "Décembre";
            
            for(int i = 0; i < 7; i++)
                calendarValues.add(new Vector<String>());
            
            String mois = moisfr[cal.get(Calendar.MONTH)];
            int annee = cal.get(Calendar.YEAR);
            label = mois + " " + annee;
            cal.set(Calendar.DAY_OF_MONTH, 1);
            int startDay = cal.get(Calendar.DAY_OF_WEEK);
            int numberOfDays = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            int weeks = cal.getActualMaximum(Calendar.WEEK_OF_MONTH);

            
            int d, m = cal.get(Calendar.MONTH) + 1, a = cal.get(Calendar.YEAR);
            int i = startDay-1;
            for(int j = 0; j < nb_colonnes*nb_lignes; j++)
            {
                if(j < i)
                    calendarValues.get(0).add("");
                else if(j - i + 1 > numberOfDays)
                    calendarValues.get(4).add("");
                else
                    calendarValues.get(j/7).add("" + (j - i + 1));
                
            }
            
    }

    public String getLabel(){
        return label;
    }
    
    @Override
    public int getRowCount() {
        return nb_lignes;
    }

    @Override
    public int getColumnCount() {
        return nb_colonnes;
    }

    @Override
    public String getValueAt(int i, int j) {
        return calendarValues.get(i).get(j);
    }

    @Override
    public String getColumnName(int c) {
        return titres[c];
    }
    
}
