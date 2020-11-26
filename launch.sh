mainCommand() {
  echo 1. Запустить тесты на Chrome
  echo 2. Запустить тесты на Firefox
  echo 3. Запустить тесты на Selenoid - Chrome
  echo 4. Запустить тесты на Selenoid - Firefox
  read -p 'Выберите номер команды:' EVENT
  handleCommand
}

handleCommand() {
  if [ $EVENT -eq 1 ]; then
      mvn clean test -Dplatform=chrome
  elif [ $EVENT -eq 2 ]; then
      mvn clean test -Dplatform=firefox
  elif [ $EVENT -eq 3 ]; then
      echo 86
      echo 85
      read -p 'Выберите версию Chrome:' CHROMEVERSION
      selenoidChrome
  elif [ $EVENT -eq 4 ]; then
      echo 82
      echo 81
      read -p 'Выберите версию Firefox:' FFVERSION
      selenoidFirefox
  fi
}

selenoidChrome() {
  if [ $CHROMEVERSION -eq 86 ]; then
      docker-compose up -d
      mvn clean test -Dplatform=selenoid -Dbrowser=chrome -Dversion=86.0
      docker-compose down
  elif [ $CHROMEVERSION -eq 85 ]; then
      docker-compose up -d
      mvn clean test -Dplatform=selenoid -Dbrowser=chrome -Dversion=85.0
      docker-compose down
  fi
}

selenoidFirefox() {
  if [ $FFVERSION -eq 82 ]; then
      docker-compose up -d
      mvn clean test -Dplatform=selenoid -Dbrowser=firefox -Dversion=82.0
      docker-compose down
  elif [ $FFVERSION -eq 81 ]; then
      docker-compose up -d
      mvn clean test -Dplatform=selenoid -Dbrowser=firefox -Dversion=81.0
      docker-compose down
  fi
}

mainCommand
