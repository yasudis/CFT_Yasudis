# CFT_Yasudis

Тестовое задание для проекта Focus Start компании ЦФТ.

Created-By: Apache Maven 3.8.6

Build-Jdk: 19.0.2

## Начало работы 
Для работы программы необходим установленный на компьютере JDK и желательны 
прописанные переменные среды для JDK. 
  
Для проверки выполнения задания необходимо скачать проект.
`Обязательно` перенести исходные файлы для теста в дирикторию `D:\test`.

jar файл находится в папке `/target/CFT_Yasudis-1.0-SNAPSHOT.jar`. 

## Сортировка
Для того, чтобы запустить сортировку необходимо в командной строке ввести команду вида:    
`java -jar` `[путь к файлу CFT_Yasudis-1.0-SNAPSHOT.jar]` `[режим сортировки: ([-а]-если по возрастанию [-d]- если по убыванию). Необязательная команда, по умолчанию сортируем по возрастанию]` `[тип данных:([-s]-если строковое [-i]-если целые числа)]` `[имя выходного файла (файл будет создан в папке D:\test)]` `[имя входного файла ( заранее положенные в папку D:\test`]  


Пример команды без указания пути к файлу:

-d -s out.txt in1.txt in2.txt (выполнение строк по убыванию)

В процессе выполнение сортировки будет выводиться список состоящий из считываемых строк из файлов. Также будут сообщаться ошибки, которые будут во время сортировки. 

В случае успешного выполнения программы будет создан (или перезаписан) выходной файл и в него будут помещены отсортированные данные.
