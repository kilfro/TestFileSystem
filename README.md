# TestFileSystem
####Задание:

В качетве ТЗ решили дать файловую систему.
 Надо написать консольное приложение "Эмулятор файловой системы в памяти". Поддерживаются следующие команды:
* help - справка по командам в эмуляторе
* exit - выход
* cd - переход в каталог
* mkdir - создание каталога
* mkfile - создание файла (файл создаётся с произвольным размером)
* ls - отображение списка файлов

Необходимо написать не просто приложение, а production ready приложение, любое творчество приветствуется.

####Описание:

1. Система поддерживает все перечисленные команды.
2. Команда **help** выводит список всех команд. Если команде передать в качестве аргумента имя другой команды, 
выведется более детально описание данной команды.
3. Команды **cd**, **mkdir** и **mkfile** работают как с абсолютными, так и с относительными путями.
4. Команда **cd** не поддерживает конструкции типа: 
`cd ../..` .