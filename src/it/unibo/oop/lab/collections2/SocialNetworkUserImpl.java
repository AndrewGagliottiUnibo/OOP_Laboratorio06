package it.unibo.oop.lab.collections2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class SocialNetworkUserImpl<U extends User> extends UserImpl implements SocialNetworkUser<U> {

    private static final int NOT_DECLARED = -1;

    private final Map<String, List<U>> followedPeople;

    //costruttore - richiamo il costruttore completo
    public SocialNetworkUserImpl(final String name, final String surname, final String user) {
        this(name, surname, user, NOT_DECLARED);
    }

    //costruttore completo
    public SocialNetworkUserImpl(final String name, final String surname, final String user, final int userAge) {
        super(name, surname, user, userAge);
        this.followedPeople = new HashMap<>();
    }

    /**
     * ho basato una mia incompleta versione con quella proposta dalle soluzioni, per quanto
     * riguarda questo primo metodo.
     * 
     * la gestione, a mio parere, l'avrei fatta creando una mappa con: una chiave
     * che indicava una persona e con valore una lista di suoi amici (UserImpl)...è vero che 
     * a livello di spazio occupato la mappa sarebbe stata enorme, perchè alla fine così si 
     * immagazzinano degli oggetti e non delle stringhe, ma sarebbe stato più diretto
     * concettualmente l'associazione Utente - Amici.
     * 
     * @param circle
     * @param user
     * @return true if operations is done
     */
    public boolean addFollowedUser(final String circle, final U user) {

        //sono obbligato a creare una nuova lista altrimenti l'istruzione a riga 44 un errore
        List<U> newFollower = this.followedPeople.get(circle);

        if (newFollower != null) {
            return newFollower.add(user);
        }
           newFollower = new ArrayList<>();
           this.followedPeople.put(circle, newFollower);
           return newFollower.add(user);
    }

    /**
     * @param groupName
     * @return a collection
     */
    public Collection<U> getFollowedUsersInGroup(final String groupName) {

        if (this.followedPeople.get(groupName) != null) {
            return new ArrayList<>(this.followedPeople.get(groupName));
        }
        return Collections.emptyList();
    }

    /**
     * @return list
     */
    public List<U> getFollowedUsers() {
        /*
         * Avevo trovato una soluzione che credevo corretta e che prevedeva l'utilizzo di un cast del metodo
         * values() richiamato su followedPeople. Ho subito notato che non fosse una soluzione corretta poichè
         * generave un errore a run-time: ClassCastException. Errore abbastanza grave ma volevo rendere partecipe
         * il professore e me stesso di questo mio scivolone. Riporto l'istruzione che ritenevo corretta:
         * 
         * final List<U> list = (List<U>) followedPeople.values();
         */
        final List<U> list = new ArrayList<>();
        for (final List<U> listAnalyzer : followedPeople.values()) {
            list.addAll(listAnalyzer);
        }
        return list;
    }
}
