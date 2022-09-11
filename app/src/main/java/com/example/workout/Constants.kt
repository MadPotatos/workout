package com.example.workout

object Constants {
    fun defaultExerciseList() : ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        val absCrunch = ExerciseModel(1,"Abdominal Crunch",R.drawable.abs_crunch,false,false)
        exerciseList.add(absCrunch)
        val dumbbellLift = ExerciseModel(2,"Lift the Dumbbbells",R.drawable.dumbbell_push,false,false)
        exerciseList.add(dumbbellLift)
        val plank = ExerciseModel(3,"Plank",R.drawable.plank,false,false)
        exerciseList.add(plank)
        val pushUp = ExerciseModel(4,"Push Up",R.drawable.push_up,false,false)
        exerciseList.add(pushUp)
        val run = ExerciseModel(5,"Run",R.drawable.run,false,false)
        exerciseList.add(run)
        val squat = ExerciseModel(6,"Squat",R.drawable.squat,false,false)
        exerciseList.add(squat)
        val wallPushUp = ExerciseModel(7,"Wall Push Up",R.drawable.wall_push_up,false,false)
        exerciseList.add(wallPushUp)
        val warmUp = ExerciseModel(8,"Warm Up",R.drawable.warm_up,false,false)
        exerciseList.add(warmUp)
        return exerciseList

    }
}