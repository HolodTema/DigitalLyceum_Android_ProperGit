package com.example.lyceumapp

import kotlin.Exception

class LessonOutOfBoundsException(): Exception()
class CantCreateRetrofitRequestException(): Exception()
class TabLayoutCanNotHaveMoreThan6TabsException(): Exception()
class NoLessonsWhenFragmentCreatedException(): Exception()
class NoDataInShPreferencesException: Exception()
class IncorrectDayOfWeekFormatException: Exception()
class TodayScheduleIsNullException: Exception()