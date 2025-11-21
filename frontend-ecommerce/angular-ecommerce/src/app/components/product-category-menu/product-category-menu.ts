import { Component, OnInit } from '@angular/core';
import { ProductCategory } from '../../common/product-category';
import { ProductService } from '../../services/product';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-product-category-menu',
  imports: [CommonModule,RouterLink],
  templateUrl: './product-category-menu.html',
  styleUrl: './product-category-menu.css',
})
export class ProductCategoryMenu implements OnInit {

  productCategories:ProductCategory[] = [];


  constructor(private productService: ProductService){}

  ngOnInit(): void {
    this.listProductcategories();
  }


  listProductcategories() {
    this.productService.getProductCategories().subscribe(
      data =>{
        this.productCategories = data;
      }
    );
  }

}
