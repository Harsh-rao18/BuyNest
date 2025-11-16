import { Component, OnInit } from '@angular/core';
import { Product } from '../../common/product';
import { ProductService } from '../../services/product';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-list',
  imports: [CommonModule],
  templateUrl: './product-list.html',
  styleUrl: './product-list.css',
})
export class ProductList implements OnInit {

  products:Product[] = [];
  currentCategoryId:number = 1;

  constructor(private productService:ProductService,private route:ActivatedRoute){}

  ngOnInit(): void {
    this.route.paramMap.subscribe(()=>{
      this.listProducts();
    })
  }

  listProducts() {
    // check if "id" parameter is avilable
    const hasCategoryId:boolean = this.route.snapshot.paramMap.has('id');
    if (hasCategoryId) {
      // get the id param string . convert string into number
      this.currentCategoryId = +this.route.snapshot.paramMap.get('id')!;
    } else {
      // not categoryId avilable ... default to category id 1
      this.currentCategoryId = 1;
    }

    this.productService.getProducts(this.currentCategoryId).subscribe(
      data => {
        this.products = data;
      }
    )
  }
}
