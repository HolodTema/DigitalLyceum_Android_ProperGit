package com.example.lyceumapp

import kotlin.Exception

class LessonOutOfBoundsException(): Exception()
class CantGenerateObjectsFromJsonException(): Exception()
class IncorrectLyceumClassGradeException(): Exception()
class NoClassGradeInSHPreferencesException(): Exception()
class CantCreateRetrofitRequestException(): Exception()
class GradeIdDidNotSavedInShPreferencesException(): Exception()
class TabLayoutCanNotHaveMoreThan6TabsException(): Exception()
class NullPresenterException: Exception()

//you need to use try/catch block to