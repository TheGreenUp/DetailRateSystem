package com.bsuir.green.service;

import com.bsuir.green.common.command.getCommands.GetRateQuestionsCommand;
import com.bsuir.green.common.response.getResponse.GetRateQuestionsRespose;
import com.bsuir.green.common.response.Response;
import com.bsuir.green.enums.DetailType;

import java.util.ArrayList;
import java.util.Optional;

public class RateService {
    private static final RateService rateService = new RateService();

    public static RateService getInstance() {
        return rateService;
    }

    public Response getQuestions(GetRateQuestionsCommand getRateQuestionsCommand) {
        ArrayList<String> questions = new ArrayList<>();
        Optional<DetailType> dtype = DetailType.get(getRateQuestionsCommand.getDetail().getDetailType());//получаем переменную типа enum
        switch (dtype.get()) {
            case CLUTCH -> {
                questions.add("Количество передач [0-10]");
                questions.add("Насколько сильные толчки испытывает автомобиль при смене передачи? [0 - толчки заметно выделяются / 10 - толчки незаметны]");
                questions.add("Оценка внутренних компонентов [0 - ужасно / 10 - идеально]");
            }
            case COOLING -> {
                questions.add("Потребление электричества от аккумулятора [0 - высокое / 10 низкое]");
                questions.add("Эффективность вентиляторов [0 - низкая / 10 - высокая]");
                questions.add("Оценка внутренних компонентов [0 - ужасно/10 - идеально]");
            }
            case STEERING -> {
                questions.add("Чувствительность к ударным нагрузкам при эксплуатации [0 - низкая / 10 - высокая]");
                questions.add("Качество датчика крутящего момента на рулевом колесе [0 - низкое / 10 - высокое]");
                questions.add("Скорость реакции автомобиля на изменение положения рулевого колеса [0 - низкая / 10 - высокая]");
            }
            case SUSPENSION -> {
                questions.add("Качество амортизации на 100Па [0 - ужасное / 10 - высокое");
                questions.add("Качество материала поддерживающей оси/крепления [0 - ужасное / 10 высокое]");
                questions.add("Износостойкость [0 - ужасная / 10 - высокое");
            }
            case FUEL_SYSTEM -> {
                questions.add("Погрешность рассчёта количества топлива [0 - высокая / 10 - низкая]");
                questions.add("Скорость подачи топлива [0 - низкая / 10 - высокая]");
                questions.add("Качество топливных фильтров [0 - ужасное / 10 высокое]");

            }
            case SPEEDOMETER -> {
                questions.add("Погрешность расчета скорости [0 - высокая / 10 - низкая]");
                questions.add("Оценка работы скоростного узла спидометра [0 - ужасно / 10 - отлично]");
                questions.add("Скорость изменения магнитной стрелки [0 - низкая / 10 высокая]");

            }
            case BRAKE_SYSTEM -> {
                questions.add("Потребление тормозной жидкости [0 - низкое / 10 - высокое]");
                questions.add("Надежность используемого при создании материала [0 - низкая / 10 - высокая]");
                questions.add("Износостойкость [0 - низкая / 10 высокая]");
            }
        }
        return new GetRateQuestionsRespose(questions);


    }
}
