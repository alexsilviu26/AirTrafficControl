
                     Air Traffic Control

        Introducere
        Tema aceasta isi propune realizarea unui sistem de control al traficului 
    aerian, folosind principiile programarii orientate pe obiecte (OOP). Scopul
    aplicatiei este de a simula procesele implicate in controlul traficului aerian:
    adaugarea si eliminarea aeroporturilor si aeronavelor, gestionarea zborurilor
    si a situatiilor de urgenta, precum si generarea diverselor rapoarte legate 
    de starea traficului aerian.

        Structura Proiectului
    Proiectul este organizat în mai multe clase, fiecare modelând o componentă 
    specifică a procesului de control al spatiului aerian. Fiecare clasă este
    implementată în fișiere separate pentru a asigura modularitatea și claritatea
    codului.

        Clasele Principale cerute in cerinta:
    
    Airplane:
        Modelează aeronavele in mod generic, clasa fiind extinsa de subclasele 
    NarrowBodyAirplane, respectiv WideBodyAirplane.
        Conține informații referitoare la avioane si zboruri precum modelul 
    avionului, ID-ul acestuia, orele de decolare si aterizare, destinatia si 
    punctul de plecare, dar si alte informatii relevante despre acestea.
        Include constructori si metode pentru gestionarea aeronavelor, obtinerea
    de date si operatii necesare.

    Main:
        Clasa principala a proiectului, care contine metoda main, unde se citesc
    comenzile si se apeleaza metodele din clasa Command pentru a indeplini 
    cerintele temei.
        In aceasta clasa se realizeaza gestionarea numitor exceptii specificate
    in cerinta, alte exceptii posibile in cadrul temei sunt gestionate in clasa
    Command.
        De asemenea pentru a stoca in mod eficient aeroporturile si aeronavele
    se folosesc HashMap-uri, Arraylist-uri. Am decis utilizarea HashMap-urilor
    pentru a stoca aeroporturile si aeronavele, deoarece acestea ofera o obtinere 
    rapida si eficienta a datelor, cu ajutorul cheilor, iar Arraylist-urile pentru
    a gestiona eficient aeronavele si pistele de decolare si aterizare, mai exact
    pentru a le obtine rapid la nevoie.
        Metoda Main gestioneaza si formarea anumitor fisiere de input si output
    care se pot utiliza pe decursul intregului program, pentru cele specifice, 
    precum cele pentru rapoarte am decis gestionarea acestora in clasa Command.

    NarrowBodyAirplane, WideBodyAirplane:
        Modeleaza anumite tipuri de aeronave, acestea fiind extinderi ale clasei
    Airplane.
        In cadrul acestora regasim suprascrierea metodei toString pentru a afisa
    informatiile aferente aeronavelor, precum si gestionarea timpilor de decolare
    si aterizare, pentru a putea fi afisate corescpunzator.

    Runway:
        Modeleaza o pista de decolare si aterizare.
        Contine informatii despre pista, precum ID-ul acesteia, daca este ocupata
    sau nu, utilizarea, dar si alte informatii relevante. 
        De asemenea, aceasta contine un TreeSet pentru a gestiona aeronavele care
    asteapta sa decoleze sau sa aterizeze, TreeSet-ul fiind sortat dupa timpul de
    decolare sau aterizare al aeronavelor, dar si dupa faptul daca acestea sunt
    in situatie de urgenta sau nu la aterizare. Aceasta structura de date a fost
    aleasa pentru a gestiona eficient aeronavele, fiind pastrate in mod unic, dar 
    si in ordinea necesara, pentru a putea fi preluate rapid.

    Clase Adiționale:
    Command:
        Implementeaza toate functionaliatile cerute de tema, folosind metodele
    specifice fiecarei clase.
        Fiecare functionalitate este un caz distinct in aceasta clasa, utilizand
    metodele din celelalte clase.
        De asemenea, gestioneaza anumite exceptii specifice temei.

    Utils
        Implementeaza o metoda necesara pentru parsarea comenzilor, aceasta fiind
    folosita in clasa Main pentru a fi citite comenzile si adaugate in ArrayList,
    structura de date folosita pentru a stoca comenzile eficient si in ordinea in
    care au fost citite.

    IncorrectRunwayException, UnavaibleRunwayException:
        Clase care modeleaza exceptiile specifice temei, acestea fiind aruncate
    in cazul in care se intalnesc anumite situatii specifice temei.

        Structuri de Date
    Proiectul utilizează mai multe structuri de date eficiente pentru a gestiona
    componentele sistemului:
        HashMap:
    Utilizat pentru stocarea aeroporturilor și aeronavelor, oferind
    acces rapid pe baza unor chei unice, ceea ce reduce timpul de căutare și 
    actualizare.
        TreeSet:
    Folosit pentru gestionarea avioanelor programate pe piste, menținând
    ordinea în funcție de priorități (timp de decolare/aterizare, urgențe). Această 
    structură asigură unicitatea și eficiența operațiilor.
        ArrayList:
    Utilizat pentru gestionarea comenzilor și pentru stocarea listelor 
    de avioane sau piste, oferind flexibilitate și acces rapid secvențial.

    Aceste structuri au fost alese pentru a asigura eficiența și claritatea implementării.

    
        Stil și Convenții
    Codul este scris folosind convenția camelCase, cu variabile private, dar si 
    protejate și metode publice.
    Limbajul folosit este limba engleza pentru a urma numele claselor definite in 
    cerinta temei.
    Încapsularea este respectată prin utilizarea variabilelor private si protejate, 
    accesibile doar prin metode publice.
    Pentru stocarea argumentelor in urma impartirii am ales folosirea unui tablou
    de string-uri, deoarece acestea sunt usor de accesat si de folosit in cadrul
    programului. Nu am considerat necesara folosirea unor structuri de date mai
    complexe, deoarece acestea ar fi adus un surplus de complexitate in cadrul
    programului.

        Concluzie
    Proiectul Air Traffic Control oferă o implementare robustă și modulară a unui 
    sistem de control al traficului aerian, utilizând principiile OOP. Structurile de 
    date eficiente (HashMap, TreeSet, ArrayList) asigură gestionarea optimă a aeroporturilor,
    aeronavelor și pistelor, iar separarea logicii pe clase facilitează întreținerea și
    extinderea aplicației. Soluția respectă cerințele și oferă un cod clar, scalabil 
    și bine documentat.
