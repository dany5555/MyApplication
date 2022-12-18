package com.vision.experienciamatutina;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class AdminActivity extends AppCompatActivity {

    Button generate1RCExamButton, generate2RCExamButton, generate3RCExamButton, generateSemiFinalExamButton, generateFinalExamButton;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference examRef1RC = database.getReference("Exam1RC");
    DatabaseReference examRef2RC = database.getReference("Exam2RC");
    DatabaseReference examRef3RC = database.getReference("Exam3RC");
    DatabaseReference examRefSemiFinal = database.getReference("ExamSemiFinal");
    DatabaseReference examRefFinal = database.getReference("ExamFinal");

    ArrayList<String> datesList = new ArrayList<>();
    ArrayList<String> titlesList = new ArrayList<>();
    ArrayList<String> verseContentsList = new ArrayList<>();
    ArrayList<String> versesList = new ArrayList<>();
    ArrayList<String> questionTypeList = new ArrayList<>();
    ArrayList<String> randomDataList = new ArrayList<>();
    ArrayList<Integer> dayIdsList = new ArrayList<>();
    ArrayList<Integer> randomNumbers = new ArrayList<>();
    ArrayList<QuestionModel> tempQuestionList = new ArrayList<>();


    ArrayList<DayObjectModel> dayObjectList = new ArrayList<>();

    public static ArrayList<QuestionModel> finalQuestionsList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        generate1RCExamButton = findViewById(R.id.generate_1rc_exam_button);
        generate2RCExamButton = findViewById(R.id.generate_2rc_exam_button);
        generate3RCExamButton = findViewById(R.id.generate_3rc_exam_button);
        generateSemiFinalExamButton = findViewById(R.id.generate_semi_final_exam_button);
        generateFinalExamButton = findViewById(R.id.generate_final_exam_button);


        generate1RCExamButton.setOnClickListener(view -> {

            getDates1RCList();
            getTitles1RCList();
            getVerseContents1RCList();
            getVerses1RCList();
            getDayIds();
            getQuestionTypeQualificationPhase();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 2 questions only per every day.
                for (int j = 0; j < 2; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i));
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRef1RC.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRef1RC.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generate2RCExamButton.setOnClickListener(view -> {

            getDates2RCList();
            getTitles2RCList();
            getVerseContents2RCList();
            getVerses2RCList();
            getDayIds();
            getQuestionTypeQualificationPhase();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 2 questions only per every day.
                for (int j = 0; j < 2; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i));
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRef2RC.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRef2RC.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });

        generate3RCExamButton.setOnClickListener(view -> {

            getDates3RCList();
            getTitles3RCList();
            getVerseContents3RCList();
            getVerses3RCList();
            getDayIds();
            getQuestionTypeQualificationPhase();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 2 questions only per every day.
                for (int j = 0; j < 2; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i));
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRef3RC.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRef3RC.child("Pregunta " + (i + 1)).setValue(questionModel);
                }
            }
        });

        generateSemiFinalExamButton.setOnClickListener(view -> {

            getDatesFinalsList();
            getTitlesFinalsList();
            getVerseContentsFinalsList();
            getVersesFinalsList();
            getDayIds();
            getQuestionTypeSemiFinal();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            for (int i = 0; i < datesList.size(); i++) {
                // 2 questions only per every day.
                for (int j = 0; j < 2; j++) {
                    // Generate a MC question
                    if (questionTypeList.get(0).equals("MC")) {
                        generateMultipleChoiceQuestion(dayIdsList.get(i));
                    }
                    // Generate a TF question
                    else if (questionTypeList.get(0).equals("TF")) {
                        generateTrueOrFalseQuestions(dayIdsList.get(i));
                    }

                    // Generate a FB question
                    else {
                        generateFillInTheBlankQuestions(dayIdsList.get(i));
                    }

                    questionTypeList.remove(0);
                }
            }

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRefSemiFinal.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRefSemiFinal.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });

        generateFinalExamButton.setOnClickListener(view -> {

            getDatesFinalsList();
            getTitlesFinalsList();
            getVerseContentsFinalsList();
            getVersesFinalsList();
            getDayIds();
            getQuestionLevelFinal();

            for (int i = 0; i < datesList.size(); i++) {
                dayObjectList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));
                //notMixedDayIdsList.add(new DayObjectModel(dayIdsList.get(i), datesList.get(i), titlesList.get(i), verseContentsList.get(i), versesList.get(i)));

            }

            Collections.shuffle(dayIdsList);

            Log.e("lol", "size: " + questionTypeList.size());

            generateLevel1Question(dayIdsList.get(0));
            generateLevel2Question(dayIdsList.get(0));
            generateLevel2Question(dayIdsList.get(1));
            generateLevel3Question(dayIdsList.get(1));
            generateLevel1Question(dayIdsList.get(2));
            generateLevel3Question(dayIdsList.get(2));
            generateLevel1Question(dayIdsList.get(3));
            generateLevel2Question(dayIdsList.get(3));
            generateLevel2Question(dayIdsList.get(4));
            generateLevel3Question(dayIdsList.get(4));
            generateLevel1Question(dayIdsList.get(5));
            generateLevel3Question(dayIdsList.get(5));
            generateLevel1Question(dayIdsList.get(6));
            generateLevel2Question(dayIdsList.get(6));

            Collections.shuffle(finalQuestionsList);

            QuestionModel questionModel = new QuestionModel();

            for (int i = 0; i < finalQuestionsList.size(); i++) {
                questionModel.setQuestion(finalQuestionsList.get(i).question);
                questionModel.setQuestionType(finalQuestionsList.get(i).questionType);
                questionModel.setAnswer(finalQuestionsList.get(i).answer);
                questionModel.setOption1(finalQuestionsList.get(i).option1);
                questionModel.setOption2(finalQuestionsList.get(i).option2);
                questionModel.setOption3(finalQuestionsList.get(i).option3);
                questionModel.setOption4(finalQuestionsList.get(i).option4);

                if (i < 9) {
                    examRefFinal.child("Pregunta " + "0" + (i + 1)).setValue(questionModel);
                } else {
                    examRefFinal.child("Pregunta " + (i + 1)).setValue(questionModel);
                }            }
        });


    }

    public ArrayList<String> getQuestionTypeQualificationPhase() {
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");

        return questionTypeList;
    }

    public ArrayList<String> getQuestionTypeSemiFinal() {
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        questionTypeList.add("TF");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("FB");
        questionTypeList.add("MC");
        questionTypeList.add("TF");
        return questionTypeList;
    }

    public ArrayList<String> getQuestionLevelFinal() {
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");
        questionTypeList.add("Level 3");
        questionTypeList.add("Level 1");
        questionTypeList.add("Level 2");

        return questionTypeList;
    }


    public ArrayList<String> getDates1RCList() {
        datesList.add("1 de Octubre");
        datesList.add("2 de Octubre");
        datesList.add("3 de Octubre");
        datesList.add("4 de Octubre");
        datesList.add("5 de Octubre");
        datesList.add("6 de Octubre");
        datesList.add("7 de Octubre");
        datesList.add("8 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates2RCList() {
        datesList.add("9 de Octubre");
        datesList.add("10 de Octubre");
        datesList.add("11 de Octubre");
        datesList.add("12 de Octubre");
        datesList.add("13 de Octubre");
        datesList.add("14 de Octubre");
        datesList.add("15 de Octubre");
        datesList.add("16 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDates3RCList() {
        datesList.add("17 de Octubre");
        datesList.add("18 de Octubre");
        datesList.add("19 de Octubre");
        datesList.add("20 de Octubre");
        datesList.add("21 de Octubre");
        datesList.add("22 de Octubre");
        datesList.add("23 de Octubre");
        datesList.add("24 de Octubre");

        return datesList;
    }

    public ArrayList<String> getDatesFinalsList() {
        datesList.add("25 de Octubre");
        datesList.add("26 de Octubre");
        datesList.add("27 de Octubre");
        datesList.add("28 de Octubre");
        datesList.add("29 de Octubre");
        datesList.add("30 de Octubre");
        datesList.add("31 de Octubre");

        return datesList;
    }

    public ArrayList<String> getTitles1RCList() {
        titlesList.add("\"El Señor es mi pastor\"");
        titlesList.add("\"Brazos fuertes\"");
        titlesList.add("\"El mejor amigo\"");
        titlesList.add("\"Ovejas sin pastor\"");
        titlesList.add("\"«Nada me falta»\"");
        titlesList.add("\"Necesitamos descanso\"");
        titlesList.add("\"No temas a nada\"");
        titlesList.add("\"El Dios que entiende\"");

        return titlesList;
    }

    public ArrayList<String> getTitles2RCList() {
        titlesList.add("\"El pastor muestra el camino\"");
        titlesList.add("\"El valle de sombra de muerte\"");
        titlesList.add("\"La vara del pastor\"");
        titlesList.add("\"Nos prepara mesa\"");
        titlesList.add("\"Las heridas de la vida\"");
        titlesList.add("\"Serás lo que quieras ser\"");
        titlesList.add("\"El mejor pastor\"");
        titlesList.add("\"El pastor de Israel\"");

        return titlesList;
    }

    public ArrayList<String> getTitles3RCList() {
        titlesList.add("\"Somos su pueblo\"");
        titlesList.add("\"Sarita la pastora\"");
        titlesList.add("\"Los ríos no te ahogarán\"");
        titlesList.add("\"Le importamos\"");
        titlesList.add("\"Dios busca a sus ovejas\"");
        titlesList.add("\"Buscar las ovejas perdidas\"");
        titlesList.add("\"Le pertenecemos\"");
        titlesList.add("\"El pastor inútil\"");

        return titlesList;
    }

    public ArrayList<String> getTitlesFinalsList() {
        titlesList.add("\"Ovejas y cabritos\"");
        titlesList.add("\"Escuchar su voz\"");
        titlesList.add("\"Él murió por nosotros\"");
        titlesList.add("\"La fe que vence al mundo\"");
        titlesList.add("\"Cambio radical\"");
        titlesList.add("\"El premio supremo\"");
        titlesList.add("\"Rescate espectacular\"");

        return titlesList;
    }

    public ArrayList<String> getVerseContents1RCList() {
        verseContentsList.add("«El Señor es mi pastor»");
        verseContentsList.add("«Pero su arco se mantuvo firme, porque sus brazos son fuertes. ¡Gracias al Dios fuerte de Jacob, al Pastor y Roca de Israel»");
        verseContentsList.add("«Dios hablaba con Moisés cara a cara, como quien habla con un amigo»");
        verseContentsList.add("«Entonces él dijo: “He visto a todo Israel esparcido por los montes, como ovejas que no tienen pastor. Jehová ha dicho: ‘Estos no tienen señor. Que cada cual vuelva a su casa en paz’”»");
        verseContentsList.add("«El Señor es mi pastor, nada me falta»");
        verseContentsList.add("«En lugares de delicados pastos me hará descansar»");
        verseContentsList.add("«Junto a aguas de reposo me pastoreará»");
        verseContentsList.add("«Confortará mi alma»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents2RCList() {
        verseContentsList.add("«Me guiará por sendas de justicia por amor de su nombre»");
        verseContentsList.add("«Aunque ande en valle de sombra de muerte, no temeré mal alguno, porque tú estarás conmigo»");
        verseContentsList.add("«Tu vara y tu cayado me infundirán aliento»");
        verseContentsList.add("«Aderezas mesa delante de mí en presencia de mis angustiadores»");
        verseContentsList.add("«Unges mi cabeza con aceite; mi copa está rebosando»");
        verseContentsList.add("«Ciertamente, el bien y la misericordia me seguirán todos los días de mi vida»");
        verseContentsList.add("«Escogió a su siervo David, el que era pastor de ovejas; lo quitó de andar tras los rebaños, para que cuidara a su pueblo, para que fuera pastor de Israel. Y David cuidó del pueblo de Dios; los cuidó y los dirigió con mano hábil y corazón sincero»");
        verseContentsList.add("«Pastor de Israel, que guías a José como a un rebaño, que tienes tu trono sobre los querubines, ¡escucha!»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContents3RCList() {
        verseContentsList.add("«Reconozcan que el Señor es Dios; él nos hizo y somos suyos; ¡somos pueblo suyo y ovejas de su prado!»");
        verseContentsList.add("«Viene como un pastor que cuida su rebaño; levanta los corderos en sus brazos, los lleva junto al pecho y atiende con cuidado a las recién paridas»");
        verseContentsList.add("«Cuando pases por las aguas, yo estaré contigo; y si por los ríos, no te anegarán»");
        verseContentsList.add("«Porque Jehová irá delante de vosotros, y vuestra retaguardia será el Dios de israel»");
        verseContentsList.add("«Yo, el Señor, digo: Yo mismo voy a encargarme del cuidado de mi rebaño»");
        verseContentsList.add("«Yo buscaré a la perdida y haré volver al redil a la descarriada, vendaré la perniquebrada y fortaleceré a la débil»");
        verseContentsList.add("«Ustedes son mis ovejas, las ovejas de mi prado. Yo soy su Dios. Yo, el Señor, lo afirmo»");
        verseContentsList.add("«¡Ay del pastor inútil que abandona su rebaño! ¡Que la espada le hiera el brazo, y el puñal le saque el ojo derecho! ¡Que del brazo quede tullido, y del ojo derecho, ciego!»");

        return verseContentsList;
    }

    public ArrayList<String> getVerseContentsFinalsList() {
        verseContentsList.add("«La gente de todas las naciones se reunirá delante de él, y él separará unos de otros, como el pastor separa las ovejas de las cabras»");
        verseContentsList.add("«El portero le abre la puerta, y el pastor llama a cada oveja por su nombre, y las ovejas reconocen su voz; las saca del redil»");
        verseContentsList.add("«Yo soy el buen pastor; el buen pastor su vida da por las ovejas»");
        verseContentsList.add("«Porque todo lo que es nacido de Dios vence al mundo; y esta es la victoria que ha vencido al mundo, nuestra fe»");
        verseContentsList.add("«Pues ustedes andaban antes como ovejas extraviadas, pero ahora han vuelto a Cristo, que los cuida como un pastor y vela por ustedes»");
        verseContentsList.add("«Y cuando aparezca el Príncipe de los pastores, vosotros recibiréis la corona incorruptible de gloria»");
        verseContentsList.add("«Ya no sufrirán hambre ni sed, ni los quemará el sol, ni el calor los molestará; porque el Cordero, que está en medio del trono, será su pastor y los guiará a manantiales de aguas de vida, y Dios secará toda lágrima de sus ojos»");

        return verseContentsList;
    }

    public ArrayList<String> getVerses1RCList() {
        versesList.add("\"Salmo 23:1\"");
        versesList.add("\"Génesis 49:24, NVI");
        versesList.add("\"Éxodo 33:11\"");
        versesList.add("\"1 Reyes 22:17, RV95\"");
        versesList.add("\"Salmo 23:1\"");
        versesList.add("\"Salmo 23:2, RV95\"");
        versesList.add("\"Salmo 23:2, RV95\"");
        versesList.add("\"Salmo 23:3, RV95\"");

        return versesList;
    }

    public ArrayList<String> getVerses2RCList() {
        versesList.add("\"Salmo 23:3, RV95\"");
        versesList.add("\"Salmo 23:4, RV95");
        versesList.add("\"Salmo 23:4, RV95");
        versesList.add("\"Salmo 23:4, RV95");
        versesList.add("\"Salmo 23:5, RV95");
        versesList.add("\"Salmo 23:6, RV95");
        versesList.add("\"Salmo 78:70-72\"");
        versesList.add("\"Salmo 80:1\"");

        return versesList;
    }

    public ArrayList<String> getVerses3RCList() {
        versesList.add("\"Salmo 100:3\"");
        versesList.add("\"Isaías 40:11");
        versesList.add("\"Isaías 43:2, RV95");
        versesList.add("\"Isaías 52:12, RV95");
        versesList.add("\"Ezequiel 34:11");
        versesList.add("\"Ezequiel 34:16, RV95");
        versesList.add("\"Ezequiel 34:31\"");
        versesList.add("\"Zacarías 11:17, NVI\"");

        return versesList;
    }

    public ArrayList<String> getVersesFinalsList() {
        versesList.add("\"Mateo 25:32\"");
        versesList.add("\"Juan 10:3");
        versesList.add("\"Juan 10:11, RV95\"");
        versesList.add("\"1 Juan 5:4, RV95\"");
        versesList.add("\"1 Pedro 2:25\"");
        versesList.add("\"1 Pedro 5:4, RV95\"");
        versesList.add("\"Apocalipsis 6:16, 17\"");

        return versesList;
    }

    public ArrayList<Integer> getDayIds() {
        for (int i = 0; i < datesList.size(); i++) {
            dayIdsList.add(i);
        }
        return dayIdsList;
    }

    public void generateMultipleChoiceQuestion(int currentQuestionId) {

        // Randomly choose 2 options among 4 and save them in a string.
        // 1:date, 2:title, 3:verseContent, 4: verse.
        randomDataArray();
        String option1 = randomDataList.get(0);
        String option2 = randomDataList.get(1);
        ArrayList<Integer> randomIDList = new ArrayList<>();

        if (option1.equals("date") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el título del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("title") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(2)).getDate());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("date") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verseContent") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(2)).getDate());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("date") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del " + dayObjectList.get(currentQuestionId).getDate() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verse") && option2.equals("date")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getDate());
            answers.add(dayObjectList.get(randomIDList.get(0)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(1)).getDate());
            answers.add(dayObjectList.get(randomIDList.get(2)).getDate());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la fecha de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getDate(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("title") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verseContent") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el título del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("title") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del título " + dayObjectList.get(currentQuestionId).getTitle() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verse") && option2.equals("title")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(0)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(1)).getTitle());
            answers.add(dayObjectList.get(randomIDList.get(2)).getTitle());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el título de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getTitle(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verseContent") && option2.equals("verse")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerse());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerse());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es la cita bíblica del versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerse(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        } else if (option1.equals("verse") && option2.equals("verseContent")) {
            // Get three random pieces of data that do not match with this question's ID.
            randomIDList = generateRandomIDMultipleChoice(currentQuestionId);
            ArrayList<String> answers = new ArrayList<>();

            answers.add(dayObjectList.get(currentQuestionId).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(0)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(1)).getVerseContent());
            answers.add(dayObjectList.get(randomIDList.get(2)).getVerseContent());

            Collections.shuffle(answers);

            // Generate and save the whole question and answers in one line.
            finalQuestionsList.add(new QuestionModel("¿Cuál es el versículo de la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + "?" + " (1 Pt.)", dayObjectList.get(currentQuestionId).getVerseContent(), answers.get(0), answers.get(1), answers.get(2), answers.get(3), "MC"));
        }
    }

    public void generateTrueOrFalseQuestions(int currentQuestionId) {

        // Randomly choose 2 options among 4 and save them in a string.
        // 1:date, 2:title, 3:verseContent, 4: verse.
        randomDataArray();
        String option1 = randomDataList.get(0);
        String option2 = randomDataList.get(1);
        String question = "", answer = "", answerOption1 = "", answerOption2 = "", randDate, randTitle, randVerseContent, randVerse;

        // Generates random number between 1 and 2 to see if question will be 1:True or 2:False.
        Random rand = new Random();
        int trueOrFalse = rand.nextInt(2 - 1 + 1) + 1;

        // If it's true, we can generate question statement by getting correct data from
        // our data array based on the id that was saved in the chosen questions for this
        // category. There are 12 possible combinations, so 12 possible cases.
        if (trueOrFalse == 1) {
            //Generate question from selected data
            if (option1.equals("date") && option2.equals("title")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("date")) {
                question = "El título " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("date") && option2.equals("verseContent")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("date")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("date") && option2.equals("verse")) {
                question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("date")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("verseContent")) {
                question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("title")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("title") && option2.equals("verse")) {
                question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("title")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verseContent") && option2.equals("verse")) {
                question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            } else if (option1.equals("verse") && option2.equals("verseContent")) {
                question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                answer = "Verdadero";
                answerOption1 = "Falso";
                answerOption2 = "Verdadero";
            }
            // If it's false, then we have to choose another random piece of data. We will
            // randomly choose if the changing value will be option1 or option2.
        } else {
            // Generates random number between 1 and 2 to see if option1 or option2 will be the
            // changing value.
            Random rand2 = new Random();
            int chosenChangeValue = rand2.nextInt(2 - 1 + 1) + 1;

            // If it's 1, then option1 will need to be changed at random.
            if (chosenChangeValue == 1) {
                if (option1.equals("date")) {
                    int randID = generateRandomID(currentQuestionId);
                    randDate = dayObjectList.get(randID).getDate();

                    if (option2.equals("title")) {
                        question = "La fecha " + randDate + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "La fecha " + randDate + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "La fecha " + randDate + " corresponde a la lección con la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("title")) {
                    int randID = generateRandomID(currentQuestionId);
                    randTitle = dayObjectList.get(randID).getTitle();

                    if (option2.equals("date")) {
                        question = "El título " + randTitle + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "El titulo " + randTitle + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "El titulo " + randTitle + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("verseContent")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerseContent = dayObjectList.get(randID).getVerseContent();

                    if (option2.equals("date")) {
                        question = "El versículo " + randVerseContent + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("title")) {
                        question = "El versículo " + randVerseContent + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verse")) {
                        question = "El versículo " + randVerseContent + " corresponde a la cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option1.equals("verse")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerse = dayObjectList.get(randID).getVerse();

                    if (option2.equals("date")) {
                        question = "La cita bíblica " + randVerse + " corresponde al " + dayObjectList.get(currentQuestionId).getDate() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("title")) {
                        question = "La cita bíblica " + randVerse + " corresponde al título " + dayObjectList.get(currentQuestionId).getTitle() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option2.equals("verseContent")) {
                        question = "La cita bíblica " + randVerse + " corresponde al versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                }
            } else if (chosenChangeValue == 2) {
                if (option2.equals("date")) {
                    int randID = generateRandomID(currentQuestionId);
                    randDate = dayObjectList.get(randID).getDate();

                    if (option1.equals("title")) {
                        question = "El título " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al " + randDate + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("title")) {
                    int randID = generateRandomID(currentQuestionId);
                    randTitle = dayObjectList.get(randID).title;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al título " + randTitle + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("verseContent")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerseContent = dayObjectList.get(randID).verseContent;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("title")) {
                        question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verse")) {
                        question = "La cita bíblica " + dayObjectList.get(currentQuestionId).getVerse() + " corresponde al versículo " + randVerseContent + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                } else if (option2.equals("verse")) {
                    int randID = generateRandomID(currentQuestionId);
                    randVerse = dayObjectList.get(randID).verse;

                    if (option1.equals("date")) {
                        question = "La fecha " + dayObjectList.get(currentQuestionId).getDate() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("title")) {
                        question = "El titulo " + dayObjectList.get(currentQuestionId).getTitle() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    } else if (option1.equals("verseContent")) {
                        question = "El versículo " + dayObjectList.get(currentQuestionId).getVerseContent() + " corresponde a la cita bíblica " + randVerse + ". (1 Pt.)";
                        answer = "Falso";
                        answerOption1 = "Falso";
                        answerOption2 = "Verdadero";
                    }
                }
            }
        }

        // Add generated question and answer to final array.
        finalQuestionsList.add(new QuestionModel(question, answer, answerOption1, answerOption2, "","", "TF"));
    }

    public void generateFillInTheBlankQuestions(int currentQuestionId) {

        String verseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        ArrayList<String> wordsList = new ArrayList<>(Arrays.asList(verseContent.split("\\s")));
        ArrayList<Integer> numberOfWords = new ArrayList<>();

        int totalWordsRemoved = (int) Math.round((wordsList.size()*30.0)/100.0);
        //Log.e("", String.valueOf(Math.round((wordsList.size()*30.0)/100.0)));

        // Generate a random number between 0 and the size of the array to replace a word
        // in the title with "______".
        for (int i = 0; i < wordsList.size(); i++) {
            numberOfWords.add(i);
        }

        Collections.shuffle(numberOfWords);

        for (int i = 0; i < totalWordsRemoved; i++) {
            String removedWord = wordsList.get(numberOfWords.get(i));
            wordsList.set(numberOfWords.get(i), "________");
        }

        // Generate and save question along with answer.
        String newTitle = "";
        for (int x = 0; x < wordsList.size(); x++) {
            newTitle += wordsList.get(x) + " ";
        }
        String question = newTitle + ". (" + totalWordsRemoved + " Pts.)";

        finalQuestionsList.add(new QuestionModel(question, "", "", "", "", "", "FB"));
    }

    public void randomDataArray() {
        randomDataList.clear();
        randomDataList.add("date");
        randomDataList.add("title");
        randomDataList.add("verseContent");
        randomDataList.add("verse");

        Collections.shuffle(randomDataList, new Random());
    }

    public ArrayList<Integer> generateRandomIDMultipleChoice(int currentQuestionId) {
        // Generates random number between 1 and the size of the total verses
        // at random.
        Random rand3 = new Random();
        int randID = rand3.nextInt(dayIdsList.size());

        ArrayList<Integer> randomIDList = new ArrayList<>();

        // Check if the random number isn't the same as the current question ID.
        for (int x = 0; x < 3; x++) {
            while (randID == dayObjectList.get(currentQuestionId).getDayId() || randomIDList.contains(randID)) {
                randID = rand3.nextInt(dayIdsList.size());
            }
            randomIDList.add(randID);
            randID = rand3.nextInt(dayIdsList.size());
        }

        Log.e("lol", "random id content: " + randomIDList.toString());


        return randomIDList;
    }

    public int generateRandomID(int i) {
        // Generates random number between 1 and 13 to get a new piece of data
        // at random.
        Random rand3 = new Random();
        int randID = rand3.nextInt(dayIdsList.size());

        // Check if the random number isn't the same as the current question ID.
        while (randID == dayObjectList.get(i).getDayId()) {
            randID = rand3.nextInt(dayIdsList.size());
        }

        return randID;

    }

    public void generateLevel1Question(int currentQuestionId) {

            String selectedDate = dayObjectList.get(currentQuestionId).getDate();
            String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
            String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
            String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título del " + selectedDate + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título del versículo " + selectedVerseContent + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título de la cita bíblica " + selectedVerse + "?", selectedTitle, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo del " + selectedDate + "?", "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo del título " + selectedTitle + "?", "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo de la cita bíblica " + selectedVerse + "?", "\""+ selectedVerseContent + "\"", "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del " + selectedDate + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del título " + selectedTitle + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la cita bíblica del versículo " + selectedVerseContent + "?", selectedVerse, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha del título " + selectedTitle + "?", selectedDate, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha del versículo " + selectedVerseContent + "?", selectedDate, "", "", "", "Nivel 1"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha de la cita bíblica " + selectedVerse + "?", selectedDate, "", "", "", "Nivel 1"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level1FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level1FinalQuestionsList);


        //Collections.shuffle(level1FinalQuestionsList);
    }

    public void generateLevel2Question(int currentQuestionId) {
        randomNumbers.clear();
        randomNumbers();

        String selectedDate = dayObjectList.get(currentQuestionId).getDate();
        String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
        String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título y el versículo del " + selectedDate + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y la cita bíblica del " + selectedDate + "?", selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo y la cita bíblica del " + selectedDate + "?", "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el versículo del título " + selectedTitle + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y la cita bíblica del título " + selectedTitle + "?", selectedDate + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el versículo y la cita bíblica del título " + selectedTitle + "?", "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el título del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedTitle, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y la cita bíblica del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y la cita bíblica el versículo " + selectedVerseContent + "?", selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 2"));

            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el título de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + selectedTitle, "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha y el versículo de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));
            tempQuestionList.add(new QuestionModel("¿Cuál es el título y el versículo de la cita bíblica " + selectedVerse + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 2"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level2FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level2FinalQuestionsList);


        //Collections.shuffle(level2FinalQuestionsList);
    }

    public void generateLevel3Question(int currentQuestionId) {

        randomNumbers.clear();
        randomNumbers();

        String selectedDate = dayObjectList.get(currentQuestionId).getDate();
        String selectedTitle = dayObjectList.get(currentQuestionId).getTitle();
        String selectedVerseContent = dayObjectList.get(currentQuestionId).getVerseContent();
        String selectedVerse = dayObjectList.get(currentQuestionId).getVerse();

            tempQuestionList.clear();

            tempQuestionList.add(new QuestionModel("¿Cuál es el título, el versículo y la cita bíblica del " + selectedDate + "?", selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el versículo y la cita bíblica del título " + selectedTitle + "?", selectedDate + "\n\n" + "\"" + selectedVerseContent + "\"" + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el título y la cita bíblica del versículo " + selectedVerseContent + "?", selectedDate + "\n\n" + selectedTitle + "\n\n" + selectedVerse, "", "", "", "Nivel 3"));
            tempQuestionList.add(new QuestionModel("¿Cuál es la fecha, el título y el versículo de la cita bíblica " + selectedVerse + "?", selectedDate + "\n\n" + selectedTitle + "\n\n" + "\"" + selectedVerseContent + "\"", "", "", "", "Nivel 3"));

            Collections.shuffle(tempQuestionList);

        finalQuestionsList.add(tempQuestionList.get(0));
            //level3FinalQuestionsList.add(tempQuestionList.get(1));
            //Collections.shuffle(level3FinalQuestionsList);


        //Collections.shuffle(level3FinalQuestionsList);
    }

    public ArrayList<Integer> randomNumbers () {
        randomNumbers.add(0);
        randomNumbers.add(1);
        randomNumbers.add(2);
        randomNumbers.add(3);
        randomNumbers.add(4);
        randomNumbers.add(5);
        randomNumbers.add(6);
        randomNumbers.add(7);

        Collections.shuffle(randomNumbers);

        return randomNumbers;
    }


}