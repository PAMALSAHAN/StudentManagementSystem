import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Student } from '../student';
import { StudentService } from '../student.service';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.css']
})
export class StudentListComponent implements OnInit {

  students:Student[];

  constructor(private studentService:StudentService,private router:Router) { }

  ngOnInit(): void {
      this.getStudentList();
  }

  private getStudentList() {

    this.studentService.getStudentList().subscribe(data =>{
      this.students=data;
    })
  }

  updateStudentDetails(id:number){
    this.router.navigate(['/update-student',id])
  }
  deleteStudentDetails(id:number){
    this.studentService.deleteStudent(id).subscribe(data=>{
      this.getStudentList();
    })
  }

  viewstudentDetails(id:number){
    this.router.navigate(['/student-detalis',id])
  }

}
